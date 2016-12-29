package org.usd232.robotics.management.apis;

import java.io.Serializable;

/**
 * The request to set the rsvp status of a user on an event
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class RsvpRequest implements Cloneable, Serializable, Comparable<RsvpRequest>
{
    private static final long serialVersionUID = -6988168251914477720L;
    /**
     * The id of the event
     * 
     * @since 1.0
     */
    public int     event;
    /**
     * <code>true</code> if the member has signed up, <code>false</code> if the member has said he/she cannot attend, or
     * <code>null</code> if {@link EventSignup#required} is <code>false</code> or the member has not decided either way
     * yet
     * 
     * @since 1.0
     */
    public Boolean rsvp;

    @Override
    public RsvpRequest clone()
    {
        return new RsvpRequest(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + event;
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
        if (!(obj instanceof RsvpRequest))
        {
            return false;
        }
        RsvpRequest other = (RsvpRequest) obj;
        if (event != other.event)
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
    public int compareTo(RsvpRequest o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("RsvpRequest [event=%s, rsvp=%s]", event, rsvp);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public RsvpRequest()
    {
    }

    /**
     * Default constructor
     *
     * @param event
     *            The id of the event      
     * @param rsvp
     *            <code>true</code> if the member has signed up, <code>false</code> if the member has said he/she cannot attend, or      
     * @since 1.0
     */
    public RsvpRequest(int event, Boolean rsvp)
    {
        this.event = event;
        this.rsvp = rsvp;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public RsvpRequest(RsvpRequest obj)
    {
        this(obj.event, obj.rsvp);
    }
}
