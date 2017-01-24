package org.usd232.robotics.management.server.messaging.messages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Semaphore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class ForgotPasswordMessage extends AbstractMessageReason
{
    private static final Logger LOG = LogManager.getLogger();
    /**
     * The semaphore for the server url
     * 
     * @since 1.0
     */
    private final Semaphore     semaphore;
    /**
     * The url of the server
     * 
     * @since 1.0
     */
    private String              server;

    @Override
    protected String getMessage(int userId, int tracking) throws SQLException
    {
        String token;
        try (PreparedStatement st = Database.prepareStatement("SELECT `resettoken` FROM `users` WHERE `id` = ?"))
        {
            st.setInt(1, userId);
            try (ResultSet res = st.executeQuery())
            {
                res.next();
                token = res.getString(1);
            }
        }
        return String.format(
                        "Reset password link:<br />\n<a href='https://team-manage.github.io/reset#%s/%s'>https://team-manage.github.io/reset#%s/%s</a><br />\nYou are receiving this message because someone used the forgot credentials page with your account.  If this was not you, just forget about this message (the link will expire in an hour).",
                        server, token, server, token);
    }

    /**
     * Sends the message to a user
     * 
     * @param userId
     *            The id of the user the message will be sent to
     * @param server
     *            The url of the server
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     */
    public void send(int userId, String server) throws SQLException
    {
        int trackingId;
        try (PreparedStatement st = Database.prepareStatement("SELECT `resettokenset` FROM `users` WHERE `id` = ?"))
        {
            st.setInt(1, userId);
            try (ResultSet res = st.executeQuery())
            {
                if (res.next())
                {
                    trackingId = Long.hashCode(res.getTimestamp(1).toInstant().getEpochSecond());
                }
                else
                {
                    return;
                }
            }
        }
        try
        {
            semaphore.acquire();
            try
            {
                this.server = server;
                send(userId, trackingId);
            }
            finally
            {
                semaphore.release();
            }
        }
        catch (InterruptedException ex)
        {
            LOG.catching(ex);
        }
    }

    /**
     * Default constructor
     * 
     * @since 1.0
     */
    public ForgotPasswordMessage()
    {
        super(Messages.FORGOT_PASSWORD_ID, null);
        semaphore = new Semaphore(1);
    }
}
