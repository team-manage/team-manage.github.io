package org.usd232.robotics.management.server.apis;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import org.usd232.robotics.management.apis.AddContactRequest;
import org.usd232.robotics.management.apis.ContactType;
import org.usd232.robotics.management.apis.EditContactRequest;
import org.usd232.robotics.management.apis.RemoveContactRequest;
import org.usd232.robotics.management.apis.StatusResponse;
import org.usd232.robotics.management.server.database.Database;
import org.usd232.robotics.management.server.routing.PostApi;
import org.usd232.robotics.management.server.session.Session;

/**
 * Contains the apis that relate to adding/removing contact methods
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class ContactApis
{
    /**
     * Adds a contact to the user
     * 
     * @param contact
     *            The contact
     * @param session
     *            The session
     * @return If it was successful
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     */
    @PostApi("/contact/add")
    public static StatusResponse add(AddContactRequest contact, Session session) throws SQLException
    {
        try (PreparedStatement st = Database.prepareStatement(
                        "INSERT INTO `contacts` (`userid`, `type`, `value`, `carrier`) VALUES (?, ?, ?, ?)"))
        {
            st.setInt(1, session.userId);
            st.setString(2, contact.type.name());
            st.setString(3, contact.type == ContactType.email ? contact.address : contact.number);
            st.setString(4, contact.provider);
            return new StatusResponse(st.execute());
        }
    }

    /**
     * Edits a user's contact
     * 
     * @param contact
     *            The contact
     * @param session
     *            The session
     * @return If it was successful
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     */
    @PostApi("/contact/edit")
    public static StatusResponse edit(EditContactRequest contact, Session session) throws SQLException
    {
        try (PreparedStatement st = Database.prepareStatement(
                        "UPDATE `contacts` SET `notifications` = ? WHERE `userid` = ? AND `type` = ? AND `value` = ?"))
        {
            Set<String> notifications = new HashSet<String>();
            if (contact.notifications.signIn.manual)
            {
                notifications.add("signin.manual");
            }
            if (contact.notifications.signIn.auto)
            {
                notifications.add("signin.auto");
            }
            if (contact.notifications.team)
            {
                notifications.add("team");
            }
            if (contact.notifications.meetings.missed)
            {
                notifications.add("meeting.missed");
            }
            if (contact.notifications.meetings.reminders)
            {
                notifications.add("meeting.reminders");
            }
            st.setString(1, String.join(",", notifications));
            st.setInt(2, session.userId);
            st.setString(3, contact.type.name());
            st.setString(4, contact.type == ContactType.email ? contact.address : contact.number);
            return new StatusResponse(st.execute());
        }
    }

    /**
     * Removes a user's contact
     * 
     * @param contact
     *            The contact
     * @param session
     *            The session
     * @return If it was successful
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     */
    @PostApi("/contact/delete")
    public static StatusResponse delete(RemoveContactRequest contact, Session session) throws SQLException
    {
        try (PreparedStatement st = Database
                        .prepareStatement("DELETE FROM `contacts` WHERE `userid` = ? AND `type` = ? AND `value` = ?"))
        {
            st.setInt(1, session.userId);
            st.setString(2, contact.type.name());
            st.setString(3, contact.type == ContactType.email ? contact.address : contact.number);
            return new StatusResponse(st.execute());
        }
    }
}
