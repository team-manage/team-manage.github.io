package org.usd232.robotics.management.server.messaging;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.usd232.robotics.management.server.database.Database;

/**
 * The base class for the reason a message was sent
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public abstract class AbstractMessageReason
{
    /**
     * The unique id for this reason
     * 
     * @since 1.0
     */
    private final int    id;
    /**
     * The type of notification to send
     * 
     * @since 1.0
     */
    private final String notificationType;

    /**
     * Gets the message content when it is sent to a user
     * 
     * @param userId
     *            The id of the user the message will be sent to
     * @param tracking
     *            The tracking number of the message
     * @return The content of the message
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     */
    protected abstract String getMessage(int userId, int tracking) throws SQLException;

    /**
     * Gets a list of all of the events with tracking ids that a single user needs to be notified of
     * 
     * @param userId
     *            The id of the user to send messages to
     * @param trackingIds
     *            A list of the ids of each event that needs to be sent to the user
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     */
    protected void getTrackingIds(int userId, List<Integer> trackingIds) throws SQLException
    {
    }

    /**
     * Sends a single message
     * 
     * @param userId
     *            The id of the user the message will be sent to
     * @param trackingId
     *            The tracking number of the message
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     */
    protected void send(int userId, int trackingId) throws SQLException
    {
        try (PreparedStatement st = Database.prepareStatement(
                        "SELECT `id` FROM `messages` WHERE `recipient` = ? AND `reason` = ? AND `tracking` = ?"))
        {
            st.setInt(1, userId);
            st.setInt(2, id);
            st.setInt(3, trackingId);
            try (ResultSet res = st.executeQuery())
            {
                if (res.next())
                {
                    return;
                }
            }
        }
        MessagingController.sendMessage(getMessage(userId, trackingId), userId, null, id, trackingId, notificationType);
    }

    /**
     * Ensures that all of the messages this reason should send have been sent
     * 
     * @param users
     *            A list of users to send to
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     */
    void update(Iterable<Integer> users) throws SQLException
    {
        List<Integer> trackingIds = new ArrayList<Integer>();
        for (int userId : users)
        {
            getTrackingIds(userId, trackingIds);
            try (PreparedStatement st = Database.prepareStatement(
                            "SELECT `tracking` FROM `messages` WHERE `recipient` = ? AND `reason` = ?"))
            {
                st.setInt(1, userId);
                st.setInt(2, id);
                try (ResultSet res = st.executeQuery())
                {
                    while (res.next())
                    {
                        trackingIds.remove((Object) res.getInt(1));
                    }
                }
            }
            if (!trackingIds.isEmpty())
            {
                for (int trackingId : trackingIds)
                {
                    MessagingController.sendMessage(getMessage(userId, trackingId), userId, null, id, trackingId,
                                    notificationType);
                }
                trackingIds.clear();
            }
        }
    }

    /**
     * Default constructor
     * 
     * @param id
     *            The unique id for this reason
     * @param notificationType
     *            The type of notification to send
     * @since 1.0
     */
    protected AbstractMessageReason(int id, String notificationType)
    {
        this.id = id;
        this.notificationType = notificationType;
    }
}
