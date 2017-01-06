package org.usd232.robotics.management.server.apis;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.usd232.robotics.management.apis.Message;
import org.usd232.robotics.management.apis.NotificationRequest;
import org.usd232.robotics.management.apis.NotificationTarget;
import org.usd232.robotics.management.apis.StatusResponse;
import org.usd232.robotics.management.server.database.Database;
import org.usd232.robotics.management.server.routing.GetApi;
import org.usd232.robotics.management.server.routing.PostApi;
import org.usd232.robotics.management.server.session.RequirePermissions;
import org.usd232.robotics.management.server.session.Session;

/**
 * Apis that have to do with messaging and notifications
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class MessagingApis
{
    /**
     * Gets the messages a user has received
     * 
     * @param session
     *            The session
     * @return The messages
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     */
    @GetApi("/recent")
    public static Message[] getMessages(Session session) throws SQLException
    {
        try (PreparedStatement st = Database.prepareStatement(
                        "SELECT `messages`.`sent`, IF(`users`.`name` IS NULL, 'system', `users`.`name`), `messages`.`content` FROM `messages` LEFT JOIN `users` ON `messages`.`sender` = `users`.`id` WHERE `messages`.`recipient`=?"))
        {
            st.setInt(1, session.userId);
            try (ResultSet res = st.executeQuery())
            {
                List<Message> messages = new ArrayList<Message>();
                while (res.next())
                {
                    messages.add(new Message(res.getDate(1), res.getTime(1), res.getString(2), res.getString(3)));
                }
                return messages.toArray(new Message[0]);
            }
        }
    }

    /**
     * Sends a message
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
    @PostApi("/notify")
    @RequirePermissions("message.send")
    public static StatusResponse send(NotificationRequest req, Session session) throws SQLException
    {
        if (req.target == NotificationTarget.team)
        {
            try (PreparedStatement st = Database.prepareStatement(
                            "INSERT INTO `messages` (`recipient`, `sender`, `content`) SELECT `id`, ?, ? FROM `users`"))
            {
                st.setInt(1, session.userId);
                st.setString(2, req.message);
                st.execute();
                return new StatusResponse(true);
            }
        }
        else
        {
            try (PreparedStatement st = Database.prepareStatement(
                            "INSERT INTO `messages` (`recipient`, `sender`, `content`) SELECT `id`, ?, ? FROM `users` WHERE `subteam` = (SELECT `subteam` FROM `users` WHERE `id` = ?)"))
            {
                st.setInt(1, session.userId);
                st.setString(2, req.message);
                st.setInt(3, session.userId);
                st.execute();
                return new StatusResponse(true);
            }
        }
    }
}
