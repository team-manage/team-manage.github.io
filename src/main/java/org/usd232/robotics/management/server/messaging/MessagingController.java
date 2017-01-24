package org.usd232.robotics.management.server.messaging;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.usd232.robotics.management.server.database.Database;

public class MessagingController
{
    public static void sendMessage(String content, int toUser, Integer fromUser, Integer reason, Integer tracking,
                    String notificationType) throws SQLException
    {
        try (PreparedStatement st = Database.prepareStatement(
                        "INSERT INTO `messages` (`recipient`, `sender`, `reason`, `tracking`, `content`) VALUES (?, ?, ?, ?, ?)"))
        {
            st.setInt(1, toUser);
            if (fromUser == null)
            {
                st.setNull(2, Types.INTEGER);
            }
            else
            {
                st.setInt(2, fromUser);
            }
            if (reason == null)
            {
                st.setNull(3, Types.INTEGER);
            }
            else
            {
                st.setInt(3, reason);
            }
            if (tracking == null)
            {
                st.setNull(4, Types.INTEGER);
            }
            else
            {
                st.setInt(4, tracking);
            }
            st.setString(5, content);
            st.execute();
        }
        try (PreparedStatement st = Database.prepareStatement(notificationType == null
                        ? "SELECT `type`, `value`, `carrier` FROM `contacts` WHERE `userid` = ?"
                        : "SELECT `type`, `value`, `carrier` FROM `contacts` WHERE `userid` = ? AND FIND_IN_SET(?, `notifications`) > 0"))
        {
            st.setInt(1, toUser);
            if (notificationType != null)
            {
                st.setString(2, notificationType);
            }
            try (ResultSet res = st.executeQuery())
            {
                while (res.next())
                {
                    // TODO Actually send the messages
                }
            }
        }
    }
}
