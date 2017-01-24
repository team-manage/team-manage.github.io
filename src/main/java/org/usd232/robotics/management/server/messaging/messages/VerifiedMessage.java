package org.usd232.robotics.management.server.messaging.messages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.usd232.robotics.management.server.database.Database;
import org.usd232.robotics.management.server.database.Settings;
import org.usd232.robotics.management.server.messaging.AbstractMessageReason;
import org.usd232.robotics.management.server.messaging.Messages;

/**
 * The message to send to a user when his/her account gets verified
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class VerifiedMessage extends AbstractMessageReason
{
    @Override
    protected String getMessage(int userId, int tracking) throws SQLException
    {
        String name;
        try (PreparedStatement st = Database.prepareStatement("SELECT `name` FROM `users` WHERE `id` = ?"))
        {
            st.setInt(1, userId);
            try (ResultSet res = st.executeQuery())
            {
                res.next();
                name = res.getString(1);
            }
        }
        return String.format("Your application has been accepted!  Welcome to %s, %s!",
                        Settings.getString("team.name", "(Team name)"), name);
    }

    /**
     * Sends the message to a user
     * 
     * @param userId
     *            The id of the user the message will be sent to
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     */
    public void send(int userId) throws SQLException
    {
        int trackingId;
        try (PreparedStatement st = Database.prepareStatement(
                        "SELECT MAX(`tracking`) FROM `messages` WHERE `recipient` = ? AND `reason` = ?"))
        {
            st.setInt(1, userId);
            st.setInt(2, Messages.UNVERIFIED_ID);
            try (ResultSet res = st.executeQuery())
            {
                res.next();
                trackingId = res.getInt(1);
            }
        }
        send(userId, trackingId);
    }

    /**
     * Default constructor
     * 
     * @since 1.0
     */
    public VerifiedMessage()
    {
        super(Messages.VERIFIED_ID, "team");
    }
}
