package org.usd232.robotics.management.apis;

import java.io.Serializable;

/**
 * The model class for a record of event attendance
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class EventAttendanceRecord implements Cloneable, Serializable, Comparable<EventAttendanceRecord>
{
    private static final long serialVersionUID = 5221609042770277965L;
    /**
     * The id of the user this record is for
     * 
     * @since 1.0
     */
    public int                id;
    /**
     * The name of the user this record is for
     * 
     * @since 1.0
     */
    public String             name;
    /**
     * If the user has RSVP'ed
     * 
     * @since 1.0
     */
    public boolean            rsvp;
    /**
     * If the user attended the event
     * 
     * @since 1.0
     */
    public boolean            attended;
    /**
     * If the user was excused from the event
     * 
     * @since 1.0
     */
    public boolean            excused;

    @Override
    public EventAttendanceRecord clone()
    {
        return new EventAttendanceRecord(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + Boolean.hashCode(rsvp);
        result = prime * result + Boolean.hashCode(attended);
        result = prime * result + Boolean.hashCode(excused);
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
        if (!(obj instanceof EventAttendanceRecord))
        {
            return false;
        }
        EventAttendanceRecord other = (EventAttendanceRecord) obj;
        if (id != other.id)
        {
            return false;
        }
        if (name == null)
        {
            if (other.name != null)
            {
                return false;
            }
        }
        else if (!name.equals(other.name))
        {
            return false;
        }
        if (rsvp != other.rsvp)
        {
            return false;
        }
        if (attended != other.attended)
        {
            return false;
        }
        if (excused != other.excused)
        {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(EventAttendanceRecord o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("EventAttendanceRecord [id=%s, name=%s, rsvp=%s, attended=%s, excused=%s]", id, name, rsvp,
                        attended, excused);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public EventAttendanceRecord()
    {
    }

    /**
     * Default constructor
     *
     * @param id
     *            The id of the user this record is for
     * @param name
     *            The name of the user this record is for
     * @param rsvp
     *            If the user has RSVP'ed
     * @param attended
     *            If the user attended the event
     * @param excused
     *            If the user was excused from the event
     * @since 1.0
     */
    public EventAttendanceRecord(int id, String name, boolean rsvp, boolean attended, boolean excused)
    {
        this.id = id;
        this.name = name;
        this.rsvp = rsvp;
        this.attended = attended;
        this.excused = excused;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public EventAttendanceRecord(EventAttendanceRecord obj)
    {
        this(obj.id, obj.name, obj.rsvp, obj.attended, obj.excused);
    }
}
