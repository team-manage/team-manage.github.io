package org.usd232.robotics.management.server.apis;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.usd232.robotics.management.apis.Event;
import org.usd232.robotics.management.apis.EventSignup;
import org.usd232.robotics.management.apis.EventTime;
import org.usd232.robotics.management.apis.EventType;
import org.usd232.robotics.management.apis.StatusIdResponse;
import org.usd232.robotics.management.apis.StatusResponse;
import org.usd232.robotics.management.server.database.Database;
import org.usd232.robotics.management.server.routing.GetApi;
import org.usd232.robotics.management.server.routing.PostApi;
import org.usd232.robotics.management.server.session.RequirePermissions;
import org.usd232.robotics.management.server.session.Session;

/**
 * Apis that have to do with events
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class EventApis
{
    private static final Logger LOG = LogManager.getLogger();

    /**
     * Gets a list of events
     * 
     * @param session
     *            The session
     * @return The list of events
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     */
    @GetApi("/events")
    public static Event[] getEvents(Session session) throws SQLException
    {
        try (PreparedStatement st = Database.prepareStatement(
                        "SELECT `events`.`id`, `events`.`type`, `events`.`name`, `events`.`location`, `events`.`date`, `events`.`signup`, `attendance`.`rsvp`,  IF(`events`.`date` < NOW(), `attendance`.`signin` IS NOT NULL, -1) FROM `events` LEFT JOIN `attendance` ON `events`.`id` = `attendance`.`eventid` AND `attendance`.`userid` = ?"))
        {
            st.setInt(1, session.userId);
            try (ResultSet res = st.executeQuery())
            {
                List<Event> events = new ArrayList<Event>();
                while (res.next())
                {
                    Date signupDeadline = res.getDate(6);
                    Date rsvp = res.getDate(7);
                    int attended = res.getInt(8);
                    events.add(new Event(res.getInt(1), EventType.valueOf(res.getString(2)), res.getString(3),
                                    res.getString(4), res.getDate(5), null,
                                    new EventSignup(signupDeadline != null, signupDeadline, rsvp == null ? null : true),
                                    attended == -1 ? null : attended == 1));
                }
                return events.toArray(new Event[0]);
            }
        }
    }

    /**
     * Gets the information about a single event
     * 
     * @param url
     *            The request url
     * @return The event object
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     */
    @GetApi("/events/*")
    @RequirePermissions("event.view")
    public static Event getEvent(String url) throws SQLException
    {
        int eventId = Integer.parseInt(url.substring(8, url.length() - 5));
        try (PreparedStatement st = Database.prepareStatement(
                        "SELECT `type`, `name`, `location`, `date`, `start`, `end`, `signup` FROM `events` WHERE `id` = ?"))
        {
            st.setInt(1, eventId);
            try (ResultSet res = st.executeQuery())
            {
                if (!res.next())
                {
                    return null;
                }
                Time start = res.getTime(5);
                Time end = res.getTime(6);
                Date deadline = res.getDate(7);
                return new Event(eventId, EventType.valueOf(res.getString(1)), res.getString(2), res.getString(3),
                                res.getDate(4), new EventTime(start == null && end == null, start, end),
                                new EventSignup(deadline != null, deadline, null), null);
            }
        }
    }

    /**
     * Adds a new event to the database
     * 
     * @return The id of the new event
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     */
    @GetApi("/event/add")
    @RequirePermissions("event.add")
    public static StatusIdResponse add() throws SQLException
    {
        Database.startTransaction("events");
        try
        {
            try (Statement st = Database.createStatement())
            {
                st.execute("INSERT INTO `events` () VALUES ()");
                try (ResultSet res = st.executeQuery("SELECT LAST_INSERT_ID()"))
                {
                    res.next();
                    Database.commitTransaction();
                    return new StatusIdResponse(true, res.getInt(1));
                }
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
     * Conditionally requires permissions for if objects are different
     * 
     * @param session
     *            The session
     * @param a
     *            The first object
     * @param b
     *            The second object
     * @param permission
     *            The permission name
     * @since 1.0
     * @throws IllegalAccessException
     *             If the user does not have the permission
     */
    private static void requirePermission(Session session, Object a, Object b, String permission)
                    throws IllegalAccessException
    {
        if (a == null)
        {
            if (b == null)
            {
                return;
            }
        }
        else if (a.equals(b))
        {
            return;
        }
        if (!session.permissions.contains(permission))
        {
            throw new IllegalAccessException("User does not have permission to edit this part of the event");
        }
    }

    /**
     * Edits an event
     * 
     * @param event
     *            The changed event
     * @param session
     *            The session
     * @return If it was successful
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     * @throws IllegalAccessException
     *             If the user does not have the permission
     */
    @PostApi("/event/edit")
    public static StatusResponse edit(Event event, Session session) throws SQLException, IllegalAccessException
    {
        Database.startTransaction("events");
        try
        {
            try (PreparedStatement st = Database.prepareStatement(
                            "SELECT `type`, `name`, `location`, `date`, `start`, `end`, `signup` FROM `events` WHERE `id` = ?"))
            {
                st.setInt(1, event.id);
                try (ResultSet res = st.executeQuery())
                {
                    if (!res.next())
                    {
                        Database.rollbackTransaction();
                        return new StatusResponse(false);
                    }
                    requirePermission(session, res.getString(1), event.type.name(), "event.edit.type");
                    requirePermission(session, res.getString(2), event.name, "event.edit.name");
                    requirePermission(session, res.getString(3), event.location, "event.edit.name");
                    requirePermission(session, res.getDate(4), event.date, "event.edit.datetime");
                    requirePermission(session, res.getTime(5), event.time == null ? null : event.time.start,
                                    "event.edit.datetime");
                    requirePermission(session, res.getTime(6), event.time == null ? null : event.time.end,
                                    "event.edit.datetime");
                    requirePermission(session, res.getDate(7), event.signup == null ? null : event.signup.deadline,
                                    "event.edit.datetime");
                }
            }
            try (PreparedStatement st = Database.prepareStatement(
                            "UPDATE `events` SET `type` = ?, `name` = ?, `location` = ?, `date` = ?, `start` = ?, `end` = ?, `signup` = ? WHERE `id` = ?"))
            {
                st.setString(1, event.type.name());
                st.setString(2, event.name);
                st.setString(3, event.location);
                st.setDate(4, event.date);
                st.setTime(5, event.time == null ? null : event.time.start);
                st.setTime(6, event.time == null ? null : event.time.end);
                st.setDate(7, event.signup == null ? null : event.signup.deadline);
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
     * Deletes an event and all attendance records related to it from the server
     * 
     * @param eventId
     *            The event id
     * @return If it was successful
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     */
    @PostApi("/event/remove")
    @RequirePermissions("event.remove")
    public static StatusResponse delete(int eventId) throws SQLException
    {
        Database.startTransaction("attendance", "events");
        try
        {
            try (PreparedStatement st = Database.prepareStatement("DELETE FROM `attendance` WHERE `eventid` = ?"))
            {
                st.setInt(1, eventId);
                st.execute();
            }
            try (PreparedStatement st = Database.prepareStatement("DELETE FROM `events` WHERE `id` = ?"))
            {
                st.setInt(1, eventId);
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
