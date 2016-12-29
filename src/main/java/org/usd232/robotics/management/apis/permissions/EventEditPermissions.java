package org.usd232.robotics.management.apis.permissions;

import java.io.Serializable;

/**
 * The permissions relating to editing events
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class EventEditPermissions implements Cloneable, Serializable, Comparable<EventEditPermissions>
{
    private static final long serialVersionUID = 4011447096747360772L;
    /**
     * If the user can modify the type of an event
     * 
     * @since 1.0
     */
    public boolean type;
    /**
     * If the user can modify the name of an event
     * 
     * @since 1.0
     */
    public boolean name;
    /**
     * If the user can modify the date or time an event takes place
     * 
     * @since 1.0
     */
    public boolean datetime;

    @Override
    public EventEditPermissions clone()
    {
        return new EventEditPermissions(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + Boolean.hashCode(type);
        result = prime * result + Boolean.hashCode(name);
        result = prime * result + Boolean.hashCode(datetime);
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
        if (!(obj instanceof EventEditPermissions))
        {
            return false;
        }
        EventEditPermissions other = (EventEditPermissions) obj;
        if (type != other.type)
        {
            return false;
        }
        if (name != other.name)
        {
            return false;
        }
        if (datetime != other.datetime)
        {
            return false;
        }
        return true;
   }

    @Override
    public int compareTo(EventEditPermissions o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("EventEditPermissions [type=%s, name=%s, datetime=%s]", type, name, datetime);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public EventEditPermissions()
    {
    }

    /**
     * Default constructor
     *
     * @param type
     *            If the user can modify the type of an event      
     * @param name
     *            If the user can modify the name of an event      
     * @param datetime
     *            If the user can modify the date or time an event takes place      
     * @since 1.0
     */
    public EventEditPermissions(boolean type, boolean name, boolean datetime)
    {
        this.type = type;
        this.name = name;
        this.datetime = datetime;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public EventEditPermissions(EventEditPermissions obj)
    {
        this(obj.type, obj.name, obj.datetime);
    }
}
