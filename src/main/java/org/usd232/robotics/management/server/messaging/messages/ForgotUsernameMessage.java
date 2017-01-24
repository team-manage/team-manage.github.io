package org.usd232.robotics.management.server.messaging.messages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import org.usd232.robotics.management.server.database.Database;
import org.usd232.robotics.management.server.messaging.AbstractMessageReason;
import org.usd232.robotics.management.server.messaging.Messages;

/**
 * The message to send to a user who has forgotten his/her password
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class ForgotUsernameMessage extends AbstractMessageReason
{
    @Override
    protected String getMessage(int userId, int tracking) throws SQLException
    {
        String username;
        try (PreparedStatement st = Database.prepareStatement("SELECT `username` FROM `users` WHERE `id` = ?"))
        {
            st.setInt(1, userId);
            try (ResultSet res = st.executeQuery())
            {
                res.next();
                username = res.getString(1);
            }
        }
        return String.format(
                        "Your username is: %s.<br />\nYou are receiving this message because someone used the forgot credentials page with your account.  If this was not you, just forget about this message.",
                        username);
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
        send(userId, Long.hashCode(Instant.now().getEpochSecond()));
    }

    /**
     * Default constructor
     * 
     * @since 1.0
     */
    public ForgotUsernameMessage()
    {
        super(Messages.FORGOT_USERNAME_ID, null);
    }
}
