package org.usd232.robotics.management.apis;

import java.io.Serializable;

import java.sql.Date;
import java.sql.Time;

/**
 * A model class representing a message sent to a user
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class Message implements Cloneable, Serializable, Comparable<Message>
{
    private static final long serialVersionUID = 471070933010696012L;
    /**
     * The date the message was sent on
     * 
     * @since 1.0
     */
    public Date   date;
    /**
     * The time the message was sent at
     * 
     * @since 1.0
     */
    public Time   time;
    /**
     * The name of the member who sent the message
     * 
     * @since 1.0
     */
    public String from;
    /**
     * The content of the message
     * 
     * @since 1.0
     */
    public String message;

    @Override
    public Message clone()
    {
        return new Message(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        result = prime * result + ((from == null) ? 0 : from.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
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
        if (!(obj instanceof Message))
        {
            return false;
        }
        Message other = (Message) obj;
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
        if (from == null)
        {
            if (other.from != null)
            {
                return false;
            }
        }
        else if (!from.equals(other.from))
        {
            return false;
        }
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
        return true;
   }

    @Override
    public int compareTo(Message o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("Message [date=%s, time=%s, from=%s, message=%s]", date, time, from, message);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public Message()
    {
    }

    /**
     * Default constructor
     *
     * @param date
     *            The date the message was sent on      
     * @param time
     *            The time the message was sent at      
     * @param from
     *            The name of the member who sent the message      
     * @param message
     *            The content of the message      
     * @since 1.0
     */
    public Message(Date date, Time time, String from, String message)
    {
        this.date = date;
        this.time = time;
        this.from = from;
        this.message = message;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public Message(Message obj)
    {
        this(obj.date, obj.time, obj.from, obj.message);
    }
}
