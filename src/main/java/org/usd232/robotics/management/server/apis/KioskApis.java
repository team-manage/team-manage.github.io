package org.usd232.robotics.management.server.apis;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.usd232.robotics.management.apis.StatusResponse;
import org.usd232.robotics.management.apis.User;
import org.usd232.robotics.management.server.database.Database;
import org.usd232.robotics.management.server.routing.GetApi;
import org.usd232.robotics.management.server.routing.PostApi;
import org.usd232.robotics.management.server.session.RequirePermissions;

/**
 * The apis relating to the kiosk
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public abstract class KioskApis
{
    private static final Logger LOG = LogManager.getLogger();

    /**
     * Gets the user from a pin number
     * 
     * @param url
     *            The url of the request
     * @return The user
     * @since 1.0
     * @throws SQLException
     *             If an error occurs in the database
     */
    @GetApi("/kiosk/*")
    @RequirePermissions("kiosk.open")
    public static User getUser(String url) throws SQLException
    {
        int pin = Integer.parseInt(url.substring(7, url.length() - 5));
        int userId;
        String name;
        String picture;
        boolean verified;
        try (PreparedStatement st = Database.prepareStatement(
                        "SELECT `id`, `name`, `picture`, `verified` FROM `users` WHERE `pin` = ? AND FIND_IN_SET('signin.kiosk', `permissions`) > 0"))
        {
            st.setInt(1, pin);
            try (ResultSet res = st.executeQuery())
            {
                if (!res.next())
                {
                    return null;
                }
                userId = res.getInt(1);
                name = res.getString(2);
                picture = res.getString(3);
                verified = res.getInt(4) != 0;
            }
        }
        int unexcused;
        try (PreparedStatement st = Database.prepareStatement(
                        "SELECT COUNT(`events`.`id`) FROM `events` LEFT JOIN `attendance` ON `attendance`.`eventid` = `events`.`id` AND `attendance`.`userid` = ? WHERE `events`.`date` < DATE(NOW()) AND `attendance`.`signin` IS NULL AND (`attendance`.`excused` IS NULL OR `attendance`.`excused` != 1)"))
        {
            st.setInt(1, userId);
            try (ResultSet res = st.executeQuery())
            {
                res.next();
                unexcused = res.getInt(1);
            }
        }
        try (PreparedStatement st = Database.prepareStatement(
                        "SELECT COUNT(`events`.`id`) FROM `events` INNER JOIN `attendance` ON `attendance`.`eventid` = `events`.`id` AND `attendance`.`userid` = ? WHERE `events`.`date` < DATE(NOW()) AND `attendance`.`signin` IS NOT NULL AND TIME(`attendance`.`signin`) > `events`.`start`"))
        {
            st.setInt(1, userId);
            try (ResultSet res = st.executeQuery())
            {
                res.next();
                return new User(userId, name, picture, verified, unexcused, res.getInt(1));
            }
        }
    }

    /**
     * Signs a user in via the kiosk
     * 
     * @param userId
     *            The id of the user to sign in
     * @return If the sign in was successful or not
     * @since 1.0
     * @throws SQLException
     *             If an error occurs with the database
     */
    @PostApi("/kiosk/signIn")
    @RequirePermissions("kiosk.open")
    public static StatusResponse signIn(int userId) throws SQLException
    {
        try
        {
            Database.startTransaction("attendance");
            int eventId;
            try (Statement st = Database.createStatement())
            {
                try (ResultSet res = st.executeQuery(
                                "SELECT `id` FROM `events` WHERE `date` = DATE(NOW()) AND ((`start` IS NULL AND `end` IS NULL) OR (ADDTIME(TIME(NOW()), '1:0:0') > `start` AND TIME(NOW()) < ADDTIME(`end`, '1:0:0')))"))
                {
                    if (!res.next())
                    {
                        LOG.info("Sign in attempted, but there is no meeting now");
                        return new StatusResponse(false);
                    }
                    eventId = res.getInt(1);
                    if (res.next())
                    {
                        LOG.error("There are multiple events concurrently!");
                        LOG.warn("Using the first event found");
                    }
                }
            }
            try (PreparedStatement st = Database
                            .prepareStatement("INSERT IGNORE INTO `attendance` (`userid`, `eventid`) VALUES (?, ?)"))
            {
                st.setInt(1, userId);
                st.setInt(2, eventId);
                st.execute();
            }
            try (PreparedStatement st = Database.prepareStatement(
                            "UPDATE `attendance` SET `signin` = NOW() WHERE `userid` = ? AND `eventid` = ?"))
            {
                st.setInt(1, userId);
                st.setInt(2, eventId);
                st.execute();
            }
            Database.commitTransaction();
            return new StatusResponse(true);
        }
        catch (SQLException ex)
        {
            Database.rollbackTransaction();
            throw ex;
        }
    }
}
