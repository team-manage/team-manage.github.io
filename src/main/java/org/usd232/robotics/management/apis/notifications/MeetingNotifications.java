package org.usd232.robotics.management.apis.notifications;

import java.io.Serializable;

/**
 * The notification settings relating to meetings
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class MeetingNotifications implements Cloneable, Serializable, Comparable<MeetingNotifications>
{
    private static final long serialVersionUID = 8854159849159355651L;
    /**
     * If the member should get a notification for every meeting he/she misses
     * 
     * @since 1.0
     */
    public boolean missed;
    /**
     * If the member should get a reminder before every meeting
     * 
     * @since 1.0
     */
    public boolean reminders;

    @Override
    public MeetingNotifications clone()
    {
        return new MeetingNotifications(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + Boolean.hashCode(missed);
        result = prime * result + Boolean.hashCode(reminders);
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
        if (!(obj instanceof MeetingNotifications))
        {
            return false;
        }
        MeetingNotifications other = (MeetingNotifications) obj;
        if (missed != other.missed)
        {
            return false;
        }
        if (reminders != other.reminders)
        {
            return false;
        }
        return true;
   }

    @Override
    public int compareTo(MeetingNotifications o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("MeetingNotifications [missed=%s, reminders=%s]", missed, reminders);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public MeetingNotifications()
    {
    }

    /**
     * Default constructor
     *
     * @param missed
     *            If the member should get a notification for every meeting he/she misses      
     * @param reminders
     *            If the member should get a reminder before every meeting      
     * @since 1.0
     */
    public MeetingNotifications(boolean missed, boolean reminders)
    {
        this.missed = missed;
        this.reminders = reminders;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public MeetingNotifications(MeetingNotifications obj)
    {
        this(obj.missed, obj.reminders);
    }
}
