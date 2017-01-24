package org.usd232.robotics.management.server.apis;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.usd232.robotics.management.apis.ContactType;
import org.usd232.robotics.management.apis.LoginRequest;
import org.usd232.robotics.management.apis.LoginResponse;
import org.usd232.robotics.management.apis.RegisterRequest;
import org.usd232.robotics.management.apis.ResetPasswordRequest;
import org.usd232.robotics.management.apis.StatusResponse;
import org.usd232.robotics.management.apis.UserContact;
import org.usd232.robotics.management.apis.UserProfile;
import org.usd232.robotics.management.apis.notifications.MeetingNotifications;
import org.usd232.robotics.management.apis.notifications.Notifications;
import org.usd232.robotics.management.apis.notifications.SignInNotifications;
import org.usd232.robotics.management.apis.permissions.AttendancePermissions;
import org.usd232.robotics.management.apis.permissions.DevicePermissions;
import org.usd232.robotics.management.apis.permissions.EventEditPermissions;
import org.usd232.robotics.management.apis.permissions.EventPermissions;
import org.usd232.robotics.management.apis.permissions.KioskPermissions;
import org.usd232.robotics.management.apis.permissions.MessagePermissions;
import org.usd232.robotics.management.apis.permissions.Permissions;
import org.usd232.robotics.management.apis.permissions.SettingsPermissions;
import org.usd232.robotics.management.apis.permissions.SignInPermissions;
import org.usd232.robotics.management.apis.permissions.UserPermissions;
import org.usd232.robotics.management.server.database.Database;
import org.usd232.robotics.management.server.messaging.Messages;
import org.usd232.robotics.management.server.routing.PostApi;
import org.usd232.robotics.management.server.session.RequirePermissions;
import org.usd232.robotics.management.server.session.StartedSessionResponse;
import spark.Request;

/**
 * The apis relating to authentication
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public abstract class AuthenticationApis
{
    private static final Logger LOG = LogManager.getLogger();

    /**
     * Hashes a password
     * 
     * @param password
     *            The password
     * @param salt
     *            The password salt
     * @return The hashed password
     * @since 1.0
     * @throws NoSuchAlgorithmException
     *             If the hashing algorithm could not be found
     * @throws UnsupportedEncodingException
     *             If the string encoding could not be found
     */
    private static byte[] hashPassword(String password, byte[] salt)
                    throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        MessageDigest digest = MessageDigest.getInstance("SHA-512");
        digest.update(salt);
        return digest.digest(password.getBytes("UTF-8"));
    }

    /**
     * Logs a user in to the server
     * 
     * @param req
     *            The request
     * @param http
     *            The http request
     * @return The response
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     * @throws NoSuchAlgorithmException
     *             If the hashing algorithm could not be found
     * @throws UnsupportedEncodingException
     *             If the string encoding could not be found
     */
    @PostApi("/authenticate")
    public static Object login(LoginRequest req, Request http)
                    throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException
    {
        Set<String> permissions;
        String picture;
        int pin;
        int userId;
        String name;
        try (PreparedStatement st = Database.prepareStatement(
                        "SELECT `id`, `password`, `salt`, `pin`, `picture`, `permissions`, `name` FROM `users` WHERE `username` = ?"))
        {
            st.setString(1, req.username);
            try (ResultSet res = st.executeQuery())
            {
                if (!res.next())
                {
                    LOG.debug("User {} was not found", req.username);
                    return new LoginResponse("failure", null, null);
                }
                byte[] given = hashPassword(req.password, res.getBytes(3));
                byte[] expected = res.getBytes(2);
                if (!Arrays.equals(given, expected))
                {
                    LOG.debug("Invalid password for user {}", req.username);
                    return new LoginResponse("failure", null, null);
                }
                permissions = new HashSet<String>();
                permissions.addAll(Arrays.asList(res.getString(6).split(",")));
                picture = String.format("http://%s/pictures/%d", http.host(), res.getInt(5));
                pin = res.getInt(4);
                userId = res.getInt(1);
                name = res.getString(7);
            }
        }
        try (PreparedStatement st = Database.prepareStatement(
                        "SELECT `type`, `value`, `carrier`, `notifications` FROM `contacts` WHERE `userid` = ?"))
        {
            st.setInt(1, userId);
            List<UserContact> contact = new ArrayList<UserContact>();
            try (ResultSet res = st.executeQuery())
            {
                while (res.next())
                {
                    ContactType type = ContactType.valueOf(res.getString(1));
                    Set<String> notifications = new HashSet<String>();
                    notifications.addAll(Arrays.asList(res.getString(4).split(",")));
                    contact.add(new UserContact(type, type == ContactType.email ? res.getString(2) : null,
                                    type == ContactType.phone ? res.getString(2) : null, res.getString(3),
                                    new Notifications(
                                                    new SignInNotifications(notifications.contains("signin.manual"),
                                                                    notifications.contains("signin.auto")),
                                                    notifications.contains("team"),
                                                    new MeetingNotifications(notifications.contains("meeting.missed"),
                                                                    notifications.contains("meeting.reminders")))));
                }
            }
            StartedSessionResponse res = new StartedSessionResponse(new LoginResponse("success", new Permissions(
                            new KioskPermissions(permissions.contains("kiosk.open")),
                            new MessagePermissions(permissions.contains("message.send")),
                            new EventPermissions(permissions.contains("event.view"), permissions.contains("event.add"),
                                            new EventEditPermissions(permissions.contains("event.edit.type"),
                                                            permissions.contains("event.edit.name"),
                                                            permissions.contains("event.edit.datetime")),
                                            permissions.contains("event.remove")),
                            new AttendancePermissions(permissions.contains("attendance.view"),
                                            permissions.contains("attendance.modify"),
                                            permissions.contains("attendance.excuse")),
                            new UserPermissions(permissions.contains("user.view"), permissions.contains("user.verify"),
                                            permissions.contains("user.unverify")),
                            new DevicePermissions(permissions.contains("device.add"),
                                            permissions.contains("device.update"),
                                            permissions.contains("device.remove")),
                            new SettingsPermissions(permissions.contains("settings.view"),
                                            permissions.contains("settings.edit")),
                            new SignInPermissions(permissions.contains("signin.kiosk"),
                                            permissions.contains("signin.code"), permissions.contains("signin.auto"))),
                            new UserProfile(contact, picture, pin, name)));
            res.session.permissions = permissions;
            res.session.userId = userId;
            return res;
        }
    }

    /**
     * Impersonates another user's login
     * 
     * @param userId
     *            The user id
     * @param http
     *            The http request
     * @return The impersonated login response
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     */
    @PostApi("/impersonate")
    @RequirePermissions({ "attendance.view", "user.view" })
    public static LoginResponse impersonate(int userId, Request http) throws SQLException
    {
        Set<String> permissions;
        String picture;
        int pin;
        String name;
        try (PreparedStatement st = Database
                        .prepareStatement("SELECT `pin`, `picture`, `permissions`, `name` FROM `users` WHERE `id` = ?"))
        {
            st.setInt(1, userId);
            try (ResultSet res = st.executeQuery())
            {
                if (!res.next())
                {
                    LOG.debug("User {} was not found", userId);
                    return new LoginResponse("failure", null, null);
                }
                permissions = new HashSet<String>();
                permissions.addAll(Arrays.asList(res.getString(3).split(",")));
                picture = String.format("http://%s/pictures/%d", http.host(), res.getInt(2));
                pin = res.getInt(1);
                name = res.getString(4);
            }
        }
        try (PreparedStatement st = Database.prepareStatement(
                        "SELECT `type`, `value`, `carrier`, `notifications` FROM `contacts` WHERE `userid` = ?"))
        {
            st.setInt(1, userId);
            List<UserContact> contact = new ArrayList<UserContact>();
            try (ResultSet res = st.executeQuery())
            {
                while (res.next())
                {
                    ContactType type = ContactType.valueOf(res.getString(1));
                    Set<String> notifications = new HashSet<String>();
                    notifications.addAll(Arrays.asList(res.getString(4).split(",")));
                    contact.add(new UserContact(type, type == ContactType.email ? res.getString(2) : null,
                                    type == ContactType.phone ? res.getString(2) : null, res.getString(3),
                                    new Notifications(
                                                    new SignInNotifications(notifications.contains("signin.manual"),
                                                                    notifications.contains("signin.auto")),
                                                    notifications.contains("team"),
                                                    new MeetingNotifications(notifications.contains("meeting.missed"),
                                                                    notifications.contains("meeting.reminders")))));
                }
            }
            return new LoginResponse("success", new Permissions(
                            new KioskPermissions(permissions.contains("kiosk.open")),
                            new MessagePermissions(permissions.contains("message.send")),
                            new EventPermissions(permissions.contains("event.view"), permissions.contains("event.add"),
                                            new EventEditPermissions(permissions.contains("event.edit.type"),
                                                            permissions.contains("event.edit.name"),
                                                            permissions.contains("event.edit.datetime")),
                                            permissions.contains("event.remove")),
                            new AttendancePermissions(permissions.contains("attendance.view"),
                                            permissions.contains("attendance.modify"),
                                            permissions.contains("attendance.excuse")),
                            new UserPermissions(permissions.contains("user.view"), permissions.contains("user.verify"),
                                            permissions.contains("user.unverify")),
                            new DevicePermissions(permissions.contains("device.add"),
                                            permissions.contains("device.update"),
                                            permissions.contains("device.remove")),
                            new SettingsPermissions(permissions.contains("settings.view"),
                                            permissions.contains("settings.edit")),
                            new SignInPermissions(permissions.contains("signin.kiosk"),
                                            permissions.contains("signin.code"), permissions.contains("signin.auto"))),
                            new UserProfile(contact, picture, pin, name));
        }
    }

    /**
     * Registers a new account to the server
     * 
     * @param req
     *            The request
     * @return The response
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     * @throws NoSuchAlgorithmException
     *             If the hashing algorithm could not be found
     * @throws UnsupportedEncodingException
     *             If the string encoding could not be found
     */
    @PostApi("/register")
    public static StatusResponse createAccount(RegisterRequest req)
                    throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException
    {
        try
        {
            Database.startTransaction("users");
            try (PreparedStatement st = Database.prepareStatement("SELECT `id` FROM `users` WHERE `username` = ?"))
            {
                st.setString(1, req.username);
                try (ResultSet res = st.executeQuery())
                {
                    if (res.next())
                    {
                        LOG.debug("Username already taken");
                        Database.rollbackTransaction();
                        return new StatusResponse(false);
                    }
                }
            }
            try (PreparedStatement st = Database.prepareStatement(
                            "INSERT INTO `users` (`username`, `password`, `salt`, `name`) VALUES (?, ?, ?, ?)"))
            {
                st.setString(1, req.username);
                st.setString(4, String.format("%s %s", req.fname, req.lname));
                byte[] salt = new byte[16];
                SecureRandom random = new SecureRandom();
                random.nextBytes(salt);
                st.setBytes(3, salt);
                st.setBytes(2, hashPassword(req.password, salt));
                st.execute();
            }
            int userId;
            try (PreparedStatement st = Database.prepareStatement("SELECT `id` FROM `users` WHERE `username` = ?"))
            {
                st.setString(1, req.username);
                try (ResultSet res = st.executeQuery())
                {
                    res.next();
                    userId = res.getInt(1);
                }
            }
            try (PreparedStatement st = Database.prepareStatement(
                            "INSERT INTO `contacts` (`userid`, `type`, `value`, `carrier`, `notifications`) VALUES (?, ?, ?, ?, ?)"))
            {
                st.setInt(1, userId);
                st.setString(2, "email");
                st.setString(3, req.email);
                st.setString(4, null);
                st.setString(5, "team");
                st.execute();
                st.setString(3, req.email2);
                st.setString(5, "team,meeting.missed");
                st.execute();
                st.setString(2, "phone");
                st.setString(3, req.phone);
                st.setString(4, req.provider);
                st.setString(5, "team,meeting.missed,meeting.reminders");
                st.execute();
            }
            Database.commitTransaction();
            Messages.REGISTER_MESSAGE.send(userId);
            return new StatusResponse(true);
        }
        catch (SQLException ex)
        {
            LOG.catching(ex);
            Database.rollbackTransaction();
            throw ex;
        }
    }

    /**
     * Resets a user's password
     * 
     * @param req
     *            The request
     * @return If it was successful
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     * @throws NoSuchAlgorithmException
     *             If the hashing algorithm could not be found
     * @throws UnsupportedEncodingException
     *             If the string encoding could not be found
     */
    @PostApi("/reset")
    public static StatusResponse resetPassword(ResetPasswordRequest req)
                    throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException
    {
        int userId;
        byte[] salt;
        try (PreparedStatement st = Database.prepareStatement(
                        "SELECT `id`, `salt` FROM `users` WHERE `resettoken` = ? AND DATE_ADD(`resettokenset`, INTERVAL 1 HOUR) > NOW()"))
        {
            st.setString(1, req.token);
            try (ResultSet res = st.executeQuery())
            {
                if (res.next())
                {
                    userId = res.getInt(1);
                    salt = res.getBytes(2);
                }
                else
                {
                    return new StatusResponse(false);
                }
            }
        }
        try (PreparedStatement st = Database.prepareStatement(
                        "UPDATE `users` SET `password` = ?, `resettoken` = NULL, `resettokenset` = NULL WHERE `id` = ?"))
        {
            st.setBytes(1, hashPassword(req.password, salt));
            st.setInt(2, userId);
            st.execute();
            return new StatusResponse(true);
        }
    }
}
