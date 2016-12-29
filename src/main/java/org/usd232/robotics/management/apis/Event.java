package org.usd232.robotics.management.apis;

import java.io.Serializable;

import java.sql.Date;

/**
 * The model class for an event
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class Event implements Cloneable, Serializable, Comparable<Event>
{
    private static final long serialVersionUID = 1478621945081439154L;
    /**
     * The id of the event
     * 
     * @since 1.0
     */
    public int         id;
    /**
     * The type of the event
     * 
     * @since 1.0
     */
    public EventType   type;
    /**
     * The name of the event
     * 
     * @since 1.0
     */
    public String      name;
    /**
     * The location the event occurs at
     * 
     * @since 1.0
     */
    public String      location;
    /**
     * The date the event occurs on
     * 
     * @since 1.0
     */
    public Date        date;
    /**
     * The time the event occurs
     * 
     * @since 1.0
     */
    public EventTime   time;
    /**
     * How the event should be signed up for
     * 
     * @since 1.0
     */
    public EventSignup signup;
    /**
     * <code>true</code> if the member attended the event, <code>false</code> if the member did not attend the event,
     * and <code>null</code> if the event has not yet occurred.
     * 
     * @since 1.0
     */
    public Boolean     attended;

    @Override
    public Event clone()
    {
        return new Event(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        result = prime * result + ((signup == null) ? 0 : signup.hashCode());
        result = prime * result + ((attended == null) ? 0 : attended.hashCode());
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
        if (!(obj instanceof Event))
        {
            return false;
        }
        Event other = (Event) obj;
        if (id != other.id)
        {
            return false;
        }
        if (type == null)
        {
            if (other.type != null)
            {
                return false;
            }
        }
        else if (!type.equals(other.type))
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
        if (location == null)
        {
            if (other.location != null)
            {
                return false;
            }
        }
        else if (!location.equals(other.location))
        {
            return false;
        }
        if (date == null)
        {
            if (other.date != null)
            {
                return false;
            }
        }
        else if (!date.equals(other.date))
        {
            return false;
        }
        if (time == null)
        {
            if (other.time != null)
            {
                return false;
            }
        }
        else if (!time.equals(other.time))
        {
            return false;
        }
        if (signup == null)
        {
            if (other.signup != null)
            {
                return false;
            }
        }
        else if (!signup.equals(other.signup))
        {
            return false;
        }
        if (attended == null)
        {
            if (other.attended != null)
            {
                return false;
            }
        }
        else if (!attended.equals(other.attended))
        {
            return false;
        }
        return true;
   }

    @Override
    public int compareTo(Event o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("Event [id=%s, type=%s, name=%s, location=%s, date=%s, time=%s, signup=%s, attended=%s]", id, type, name, location, date, time, signup, attended);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public Event()
    {
    }

    /**
     * Default constructor
     *
     * @param id
     *            The id of the event      
     * @param type
     *            The type of the event      
     * @param name
     *            The name of the event      
     * @param location
     *            The location the event occurs at      
     * @param date
     *            The date the event occurs on      
     * @param time
     *            The time the event occurs      
     * @param signup
     *            How the event should be signed up for      
     * @param attended
     *            <code>true</code> if the member attended the event, <code>false</code> if the member did not attend the event,      
     * @since 1.0
     */
    public Event(int id, EventType type, String name, String location, Date date, EventTime time, EventSignup signup, Boolean attended)
    {
        this.id = id;
        this.type = type;
        this.name = name;
        this.location = location;
        this.date = date;
        this.time = time;
        this.signup = signup;
        this.attended = attended;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public Event(Event obj)
    {
        this(obj.id, obj.type, obj.name, obj.location, obj.date, obj.time, obj.signup, obj.attended);
    }
}
