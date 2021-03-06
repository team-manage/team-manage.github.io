package org.usd232.robotics.management.server.apis;

import java.security.SecureRandom;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.usd232.robotics.management.apis.Event;
import org.usd232.robotics.management.apis.EventSignup;
import org.usd232.robotics.management.apis.EventType;
import org.usd232.robotics.management.apis.ExcuseRequest;
import org.usd232.robotics.management.apis.ForgotCredentialsRequest;
import org.usd232.robotics.management.apis.StatusResponse;
import org.usd232.robotics.management.apis.User;
import org.usd232.robotics.management.server.database.Database;
import org.usd232.robotics.management.server.messaging.Messages;
import org.usd232.robotics.management.server.routing.GetApi;
import org.usd232.robotics.management.server.routing.PostApi;
import org.usd232.robotics.management.server.session.RequirePermissions;
import spark.Request;

/**
 * Apis relating to user management
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class UserApis
{
    private static final Logger LOG = LogManager.getLogger();

    /**
     * Gets a list of users who are registered on the server
     * 
     * @return The list of users
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     */
    @GetApi("/users")
    @RequirePermissions("user.view")
    public static User[] getUsers() throws SQLException
    {
        List<User> users = new ArrayList<User>();
        try (Statement st = Database.createStatement())
        {
            try (ResultSet res = st.executeQuery("SELECT `id`, `name`, `verified` FROM `users`"))
            {
                while (res.next())
                {
                    users.add(new User(res.getInt(1), res.getString(2), null, res.getBoolean(3), 0, 0));
                }
            }
        }
        try (PreparedStatement st = Database.prepareStatement(
                        "SELECT COUNT(`meetings`.`id`) FROM `meetings` LEFT JOIN `attendance` ON `attendance`.`eventid` = `meetings`.`id` AND `attendance`.`userid` = ? WHERE `meetings`.`date` < DATE(NOW()) AND `attendance`.`signin` IS NULL AND (`attendance`.`excused` IS NULL OR `attendance`.`excused` != 1)"))
        {
            for (User user : users)
            {
                st.setInt(1, user.id);
                try (ResultSet res = st.executeQuery())
                {
                    res.next();
                    user.unexcused = res.getInt(1);
                }
            }
        }
        try (PreparedStatement st = Database.prepareStatement(
                        "SELECT COUNT(`meetings`.`id`) FROM `meetings` INNER JOIN `attendance` ON `attendance`.`eventid` = `meetings`.`id` AND `attendance`.`userid` = ? WHERE `meetings`.`date` < DATE(NOW()) AND `attendance`.`signin` IS NOT NULL AND TIME(`attendance`.`signin`) > `meetings`.`start`"))
        {
            for (User user : users)
            {
                st.setInt(1, user.id);
                try (ResultSet res = st.executeQuery())
                {
                    res.next();
                    user.late = res.getInt(1);
                }
            }
        }
        return users.toArray(new User[0]);
    }

    /**
     * Gets the attendance records for a user
     * 
     * @param url
     *            The request url
     * @return The list of events
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     */
    @GetApi("/attendance/*")
    @RequirePermissions("attendance.view")
    public static Event[] getUserAttendance(String url) throws SQLException
    {
        int userId = Integer.parseInt(url.substring(12, url.length() - 5));
        try (PreparedStatement st = Database.prepareStatement(
                        "SELECT `meetings`.`id`, `meetings`.`type`, `meetings`.`name`, `meetings`.`location`, `meetings`.`date`, `meetings`.`signup`, `attendance`.`rsvp`,  IF(`meetings`.`date` < DATE(NOW()), `attendance`.`signin` IS NOT NULL, -1), IF(`meetings`.`date` < DATE(NOW()), TIME(`attendance`.`signin`) > `meetings`.`start`, -1) FROM `meetings` LEFT JOIN `attendance` ON `meetings`.`id` = `attendance`.`eventid` AND `attendance`.`userid` = ?"))
        {
            st.setInt(1, userId);
            try (ResultSet res = st.executeQuery())
            {
                List<Event> events = new ArrayList<Event>();
                while (res.next())
                {
                    Date signupDeadline = res.getDate(6);
                    Date rsvp = res.getDate(7);
                    int attended = res.getInt(8);
                    int late = res.getInt(9);
                    events.add(new Event(res.getInt(1), EventType.valueOf(res.getString(2)), res.getString(3),
                                    res.getString(4), res.getDate(5), null,
                                    new EventSignup(signupDeadline != null, signupDeadline, rsvp == null ? null : true),
                                    attended == -1 ? null : attended == 1, late == -1 ? null : late == 1));
                }
                return events.toArray(new Event[0]);
            }
        }
    }

    /**
     * Marks an absence as excused
     * 
     * @param req
     *            The request
     * @return If it was successful
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     */
    @PostApi("/attendance/excuse")
    @RequirePermissions("attendance.excuse")
    public static StatusResponse excuse(ExcuseRequest req) throws SQLException
    {
        Database.startTransaction("attendance");
        try
        {
            try (PreparedStatement st = Database
                            .prepareStatement("INSERT IGNORE INTO `attendance` (`userid`, `eventid`) VALUES (?, ?)"))
            {
                st.setInt(1, req.user);
                st.setInt(2, req.event);
                st.execute();
            }
            try (PreparedStatement st = Database.prepareStatement(
                            "UPDATE `attendance` SET `excused` = ? WHERE `userid` = ? AND `eventid` = ?"))
            {
                st.setBoolean(1, req.excused);
                st.setInt(2, req.user);
                st.setInt(3, req.event);
                st.execute();
                Database.commitTransaction();
                return new StatusResponse(true);
            }
        }
        catch (SQLException ex)
        {
            LOG.catching(ex);
            Database.rollbackTransaction();
            throw ex;
        }
    }

    /**
     * Verifies a user
     * 
     * @param userId
     *            The user id
     * @return If it was successful
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     */
    @PostApi("/verify")
    @RequirePermissions("user.verify")
    public static StatusResponse verify(int userId) throws SQLException
    {
        Database.startTransaction("users");
        try
        {
            int pin;
            try (Statement st = Database.createStatement())
            {
                try (ResultSet res = st.executeQuery(
                                "SELECT FLOOR(RAND() * 1000) AS `pin` FROM `users` WHERE 'pin' NOT IN (SELECT `pin` FROM `users`) LIMIT 1"))
                {
                    if (res.next())
                    {
                        pin = res.getInt(1);
                    }
                    else
                    {
                        pin = new Random().nextInt(1000);
                    }
                }
            }
            try (PreparedStatement st = Database
                            .prepareStatement("UPDATE `users` SET `verified` = 1, `pin` = ? WHERE `id` = ?"))
            {
                st.setInt(1, pin);
                st.setInt(2, userId);
                Database.commitTransaction();
                Messages.VERIFIED_MESSAGE.send(userId);
                return new StatusResponse(st.executeUpdate() == 1);
            }
        }
        catch (SQLException ex)
        {
            LOG.catching(ex);
            Database.rollbackTransaction();
            throw ex;
        }
    }

    /**
     * Unverifies a user
     * 
     * @param userId
     *            The user id
     * @return If it was successful
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     */
    @PostApi("/unverify")
    @RequirePermissions("user.unverify")
    public static StatusResponse unverify(int userId) throws SQLException
    {
        try (PreparedStatement st = Database
                        .prepareStatement("UPDATE `users` SET `verified` = 0, `pin` = NULL WHERE `id` = ?"))
        {
            st.setInt(1, userId);
            Messages.UNVERIFIED_MESSAGE.send(userId);
            return new StatusResponse(st.executeUpdate() == 1);
        }
    }

    /**
     * Sends the user their username or a link to reset their password
     * 
     * @param req
     *            The request
     * @param http
     *            The http request
     * @return If it was successful
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     */
    @PostApi("/forgot")
    public static StatusResponse forgotCredentials(ForgotCredentialsRequest req, Request http) throws SQLException
    {
        List<Integer> users = new ArrayList<Integer>();
        switch (req.known)
        {
            case email:
                try (PreparedStatement st = Database.prepareStatement(
                                "SELECT `userid` FROM `contacts` WHERE `type` = 'email' AND `value` = ?"))
                {
                    st.setString(1, req.value);
                    try (ResultSet res = st.executeQuery())
                    {
                        while (res.next())
                        {
                            users.add(res.getInt(1));
                        }
                    }
                }
                break;
            case phone:
                try (PreparedStatement st = Database.prepareStatement(
                                "SELECT `userid` FROM `contacts` WHERE `type` = 'phone' AND `value` = ?"))
                {
                    st.setString(1, req.value);
                    try (ResultSet res = st.executeQuery())
                    {
                        while (res.next())
                        {
                            users.add(res.getInt(1));
                        }
                    }
                }
                break;
            case username:
                try (PreparedStatement st = Database.prepareStatement("SELECT `id` FROM `users` WHERE `username` = ?"))
                {
                    st.setString(1, req.value);
                    try (ResultSet res = st.executeQuery())
                    {
                        if (res.next())
                        {
                            users.add(res.getInt(1));
                        }
                        else
                        {
                            return new StatusResponse(false);
                        }
                    }
                }
                break;
            default:
                LOG.warn("Unknown credential type '{}'", req.known);
                return new StatusResponse(false);
        }
        switch (req.forgot)
        {
            case password:
                String server = Base64.getEncoder()
                                .encodeToString(http.url().replaceAll("/forgot\\.json$", "").getBytes());
                try (PreparedStatement st = Database.prepareStatement(
                                "UPDATE `users` SET `resettoken` = ?, `resettokenset` = NOW() WHERE `id` = ?"))
                {
                    for (int userId : users)
                    {
                        byte[] tokenBytes = new byte[32];
                        SecureRandom random = new SecureRandom();
                        random.nextBytes(tokenBytes);
                        char[] tokenChars = new char[64];
                        for (int i = 0; i < tokenBytes.length; ++i)
                        {
                            System.arraycopy(String.format("%02x", tokenBytes[i]).toCharArray(), 0, tokenChars, i * 2,
                                            2);
                        }
                        String token = new String(tokenChars);
                        st.setString(1, token);
                        st.setInt(2, userId);
                        st.execute();
                        Messages.FORGOT_PASSWORD_MESSAGE.send(userId, server);
                    }
                }
                break;
            case username:
                for (int userId : users)
                {
                    Messages.FORGOT_USERNAME_MESSAGE.send(userId);
                }
                break;
            default:
                LOG.warn("Unknown credential type '{}'", req.forgot);
                return new StatusResponse(false);
        }
        return new StatusResponse(true);
    }
}
