package org.usd232.robotics.management.server.apis;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.usd232.robotics.management.apis.RsvpRequest;
import org.usd232.robotics.management.apis.StatusResponse;
import org.usd232.robotics.management.server.database.Database;
import org.usd232.robotics.management.server.routing.BinaryResponse;
import org.usd232.robotics.management.server.routing.GetApi;
import org.usd232.robotics.management.server.routing.PostApi;
import org.usd232.robotics.management.server.session.Session;

/**
 * Apis that relate to user profiles but do not fall in to other api classes
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class ProfileApis
{
    private static final Logger LOG = LogManager.getLogger();

    /**
     * Gets the data for a picture
     * 
     * @param url
     *            The url to the picture
     * @return The data in the picture
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     */
    @GetApi("/pictures/*")
    public static BinaryResponse getPicture(String url) throws SQLException
    {
        int id = Integer.parseInt(url.substring(url.lastIndexOf('/') + 1));
        try (PreparedStatement st = Database.prepareStatement("SELECT `mime`, `data` FROM `pictures` WHERE `id` = ?"))
        {
            st.setInt(1, id);
            try (ResultSet res = st.executeQuery())
            {
                if (!res.next())
                {
                    return null;
                }
                return new BinaryResponse(res.getString(1), res.getBytes(2));
            }
        }
    }

    /**
     * Sets the profile picture for a user
     * 
     * @param dataUrl
     *            The data url for the image
     * @param session
     *            The session
     * @return If it was successful
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     */
    @PostApi("/setPicture")
    public static StatusResponse setPicture(String dataUrl, Session session) throws SQLException
    {
        Database.startTransaction("users", "pictures");
        try
        {
            String[] urlParts = dataUrl.split("[:;,]");
            byte[] data = Base64.getDecoder().decode(urlParts[urlParts.length - 1]);
            int pictureId;
            try (PreparedStatement st = Database.prepareStatement("SELECT `picture` FROM `users` WHERE `id` = ?"))
            {
                st.setInt(1, session.userId);
                try (ResultSet res = st.executeQuery())
                {
                    if (res.next())
                    {
                        pictureId = res.getInt(1);
                    }
                    else
                    {
                        pictureId = 0;
                    }
                }
            }
            if (pictureId == 0)
            {
                try (Statement st = Database.createStatement())
                {
                    st.execute("INSERT INTO `pictures` () VALUES ()");
                    try (ResultSet res = st.executeQuery("SELECT LAST_INSERT_ID()"))
                    {
                        res.next();
                        pictureId = res.getInt(1);
                    }
                }
                try (PreparedStatement st = Database
                                .prepareStatement("UPDATE `users` SET `picture` = ? WHERE `id` = ?"))
                {
                    st.setInt(1, pictureId);
                    st.setInt(2, session.userId);
                    st.execute();
                }
            }
            try (PreparedStatement st = Database
                            .prepareStatement("UPDATE `pictures` SET `mime` = ?, `data` = ? WHERE `id` = ?"))
            {
                st.setString(1, urlParts[1]);
                st.setBytes(2, data);
                st.setInt(3, pictureId);
                st.execute();
            }
            Database.commitTransaction();
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
     * RSVPs a user
     * 
     * @param req
     *            The request
     * @param session
     *            The session
     * @return If it was successful
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     */
    @PostApi("/event/rsvp")
    public static StatusResponse rsvp(RsvpRequest req, Session session) throws SQLException
    {
        Database.startTransaction("attendance");
        try
        {
            try (PreparedStatement st = Database
                            .prepareStatement("INSERT IGNORE INTO `attendance` (`userid`, `eventid`) VALUES (?, ?)"))
            {
                st.setInt(1, session.userId);
                st.setInt(2, req.event);
                st.execute();
            }
            try (PreparedStatement st = Database.prepareStatement(
                            "UPDATE `attendance` SET `rsvp` = ? WHERE `userid` = ? AND `eventid` = ?"))
            {
                if (req.rsvp == null)
                {
                    st.setDate(1, null);
                }
                else if (req.rsvp)
                {
                    st.setDate(1, new Date(System.currentTimeMillis()));
                }
                else
                {
                    st.setDate(1, new Date(1));
                }
                st.setInt(2, session.userId);
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
     * Requests to change the user's pin number
     * 
     * @param newPin
     *            The new pin number
     * @param session
     *            The session
     * @return If the change was successful
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     */
    @PostApi("/changePin")
    public static StatusResponse changePin(int newPin, Session session) throws SQLException
    {
        Database.startTransaction("users");
        try
        {
            try (PreparedStatement st = Database.prepareStatement("SELECT `id` FROM `users` WHERE `pin` = ?"))
            {
                st.setInt(1, newPin);
                try (ResultSet res = st.executeQuery())
                {
                    if (res.next())
                    {
                        Database.rollbackTransaction();
                        return new StatusResponse(false);
                    }
                }
            }
            try (PreparedStatement st = Database.prepareStatement("UPDATE `users` SET `pin` = ? WHERE `id` = ?"))
            {
                st.setInt(1, newPin);
                st.setInt(2, session.userId);
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
}
