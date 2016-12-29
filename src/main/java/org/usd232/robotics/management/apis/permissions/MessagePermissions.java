package org.usd232.robotics.management.apis.permissions;

import java.io.Serializable;

/**
 * The permissions relating to messages
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class MessagePermissions implements Cloneable, Serializable, Comparable<MessagePermissions>
{
    private static final long serialVersionUID = -8611621755924836158L;
    /**
     * If the user can send messages to the team
     * 
     * @since 1.0
     */
    public boolean send;

    @Override
    public MessagePermissions clone()
    {
        return new MessagePermissions(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + Boolean.hashCode(send);
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
        if (!(obj instanceof MessagePermissions))
        {
            return false;
        }
        MessagePermissions other = (MessagePermissions) obj;
        if (send != other.send)
        {
            return false;
        }
        return true;
   }

    @Override
    public int compareTo(MessagePermissions o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("MessagePermissions [send=%s]", send);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public MessagePermissions()
    {
    }

    /**
     * Default constructor
     *
     * @param send
     *            If the user can send messages to the team      
     * @since 1.0
     */
    public MessagePermissions(boolean send)
    {
        this.send = send;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public MessagePermissions(MessagePermissions obj)
    {
        this(obj.send);
    }
}
