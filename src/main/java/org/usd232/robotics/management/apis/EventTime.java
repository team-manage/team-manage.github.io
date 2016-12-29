package org.usd232.robotics.management.apis;

import java.io.Serializable;

import java.sql.Time;

/**
 * The time an event or meeting is
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class EventTime implements Cloneable, Serializable, Comparable<EventTime>
{
    private static final long serialVersionUID = -1109895483052166318L;
    /**
     * If the event takes up the entire day
     * 
     * @since 1.0
     */
    public boolean allDay;
    /**
     * The time the event starts
     * 
     * @since 1.0
     */
    public Time    start;
    /**
     * The time the event ends
     * 
     * @since 1.0
     */
    public Time    end;

    @Override
    public EventTime clone()
    {
        return new EventTime(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + Boolean.hashCode(allDay);
        result = prime * result + ((start == null) ? 0 : start.hashCode());
        result = prime * result + ((end == null) ? 0 : end.hashCode());
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
        if (!(obj instanceof EventTime))
        {
            return false;
        }
        EventTime other = (EventTime) obj;
        if (allDay != other.allDay)
        {
            return false;
        }
        if (start == null)
        {
            if (other.start != null)
            {
                return false;
            }
        }
        else if (!start.equals(other.start))
        {
            return false;
        }
        if (end == null)
        {
            if (other.end != null)
            {
                return false;
            }
        }
        else if (!end.equals(other.end))
        {
            return false;
        }
        return true;
   }

    @Override
    public int compareTo(EventTime o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("EventTime [allDay=%s, start=%s, end=%s]", allDay, start, end);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public EventTime()
    {
    }

    /**
     * Default constructor
     *
     * @param allDay
     *            If the event takes up the entire day      
     * @param start
     *            The time the event starts      
     * @param end
     *            The time the event ends      
     * @since 1.0
     */
    public EventTime(boolean allDay, Time start, Time end)
    {
        this.allDay = allDay;
        this.start = start;
        this.end = end;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public EventTime(EventTime obj)
    {
        this(obj.allDay, obj.start, obj.end);
    }
}
