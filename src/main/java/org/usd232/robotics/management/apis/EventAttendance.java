package org.usd232.robotics.management.apis;

import java.io.Serializable;
import java.util.List;

/**
 * The data to show the attendance for a single event with
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class EventAttendance implements Cloneable, Serializable, Comparable<EventAttendance>
{
    private static final long          serialVersionUID = -5132581212296990048L;
    /**
     * The id of the event
     * 
     * @since 1.0
     */
    public int                         id;
    /**
     * If the signup is required for the event
     * 
     * @since 1.0
     */
    public boolean                     signupRequired;
    /**
     * A list of all of the users and how they attended the event
     * 
     * @since 1.0
     */
    public List<EventAttendanceRecord> users;

    @Override
    public EventAttendance clone()
    {
        return new EventAttendance(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + Boolean.hashCode(signupRequired);
        result = prime * result + ((users == null) ? 0 : users.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (!(obj instanceof EventAttendance))
        {
            return false;
        }
        EventAttendance other = (EventAttendance) obj;
        if (id != other.id)
        {
            return false;
        }
        if (signupRequired != other.signupRequired)
        {
            return false;
        }
        if (users == null)
        {
            if (other.users != null)
            {
                return false;
            }
        }
        else if (!users.equals(other.users))
        {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(EventAttendance o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("EventAttendance [id=%s, signupRequired=%s, users=%s]", id, signupRequired, users);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public EventAttendance()
    {
    }

    /**
     * Default constructor
     *
     * @param id
     *            The id of the event
     * @param signupRequired
     *            If the signup is required for the event
     * @param users
     *            A list of all of the users and how they attended the event
     * @since 1.0
     */
    public EventAttendance(int id, boolean signupRequired, List<EventAttendanceRecord> users)
    {
        this.id = id;
        this.signupRequired = signupRequired;
        this.users = users;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public EventAttendance(EventAttendance obj)
    {
        this(obj.id, obj.signupRequired, obj.users);
    }
}
