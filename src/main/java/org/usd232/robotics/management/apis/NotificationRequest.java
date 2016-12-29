package org.usd232.robotics.management.apis;

import java.io.Serializable;

/**
 * The request to send a notification
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class NotificationRequest implements Cloneable, Serializable, Comparable<NotificationRequest>
{
    private static final long serialVersionUID = 6211783957529837753L;
    /**
     * The message to send
     * 
     * @since 1.0
     */
    public String             message;
    /**
     * The target to send the message to
     * 
     * @since 1.0
     */
    public NotificationTarget target;

    @Override
    public NotificationRequest clone()
    {
        return new NotificationRequest(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((target == null) ? 0 : target.hashCode());
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
        if (!(obj instanceof NotificationRequest))
        {
            return false;
        }
        NotificationRequest other = (NotificationRequest) obj;
        if (message == null)
        {
            if (other.message != null)
            {
                return false;
            }
        }
        else if (!message.equals(other.message))
        {
            return false;
        }
        if (target == null)
        {
            if (other.target != null)
            {
                return false;
            }
        }
        else if (!target.equals(other.target))
        {
            return false;
        }
        return true;
   }

    @Override
    public int compareTo(NotificationRequest o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("NotificationRequest [message=%s, target=%s]", message, target);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public NotificationRequest()
    {
    }

    /**
     * Default constructor
     *
     * @param message
     *            The message to send      
     * @param target
     *            The target to send the message to      
     * @since 1.0
     */
    public NotificationRequest(String message, NotificationTarget target)
    {
        this.message = message;
        this.target = target;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public NotificationRequest(NotificationRequest obj)
    {
        this(obj.message, obj.target);
    }
}
