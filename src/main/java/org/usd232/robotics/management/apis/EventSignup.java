package org.usd232.robotics.management.apis;

import java.io.Serializable;

import java.sql.Date;

/**
 * How events need to be signed up for
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class EventSignup implements Cloneable, Serializable, Comparable<EventSignup>
{
    private static final long serialVersionUID = -5627696329518660509L;
    /**
     * If the signup is required for the event
     * 
     * @since 1.0
     */
    public boolean required;
    /**
     * The date that the members need to sign up before in order to go to the event
     * 
     * @since 1.0
     */
    public Date    deadline;
    /**
     * <code>true</code> if the member has signed up, <code>false</code> if the member has said he/she cannot attend, or
     * <code>null</code> if {@link EventSignup#required} is <code>false</code> or the member has not decided either way
     * yet
     * 
     * @since 1.0
     */
    public Boolean rsvp;

    @Override
    public EventSignup clone()
    {
        return new EventSignup(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + Boolean.hashCode(required);
        result = prime * result + ((deadline == null) ? 0 : deadline.hashCode());
        result = prime * result + ((rsvp == null) ? 0 : rsvp.hashCode());
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
        if (!(obj instanceof EventSignup))
        {
            return false;
        }
        EventSignup other = (EventSignup) obj;
        if (required != other.required)
        {
            return false;
        }
        if (deadline == null)
        {
            if (other.deadline != null)
            {
                return false;
            }
        }
        else if (!deadline.equals(other.deadline))
        {
            return false;
        }
        if (rsvp == null)
        {
            if (other.rsvp != null)
            {
                return false;
            }
        }
        else if (!rsvp.equals(other.rsvp))
        {
            return false;
        }
        return true;
   }

    @Override
    public int compareTo(EventSignup o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("EventSignup [required=%s, deadline=%s, rsvp=%s]", required, deadline, rsvp);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public EventSignup()
    {
    }

    /**
     * Default constructor
     *
     * @param required
     *            If the signup is required for the event      
     * @param deadline
     *            The date that the members need to sign up before in order to go to the event      
     * @param rsvp
     *            <code>true</code> if the member has signed up, <code>false</code> if the member has said he/she cannot attend, or      
     * @since 1.0
     */
    public EventSignup(boolean required, Date deadline, Boolean rsvp)
    {
        this.required = required;
        this.deadline = deadline;
        this.rsvp = rsvp;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public EventSignup(EventSignup obj)
    {
        this(obj.required, obj.deadline, obj.rsvp);
    }
}
