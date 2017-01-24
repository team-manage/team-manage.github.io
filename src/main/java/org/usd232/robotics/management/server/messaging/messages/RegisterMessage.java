package org.usd232.robotics.management.server.messaging.messages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.usd232.robotics.management.server.database.Database;
import org.usd232.robotics.management.server.database.Settings;
import org.usd232.robotics.management.server.messaging.AbstractMessageReason;
import org.usd232.robotics.management.server.messaging.Messages;

/**
 * The message the user is sent when he/she registers
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class RegisterMessage extends AbstractMessageReason
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
        return String.format(
                        "Hello, %s.<br />\nWelcome to %s.  Thank you for applying to this team.  An administrator will review your application shortly.",
                        name, Settings.getString("team.name", "(Team name)"));
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
        send(userId, 0);
    }

    /**
     * Default constructor
     * 
     * @since 1.0
     */
    public RegisterMessage()
    {
        super(Messages.REGISTER_MESSAGE_ID, null);
    }
}
