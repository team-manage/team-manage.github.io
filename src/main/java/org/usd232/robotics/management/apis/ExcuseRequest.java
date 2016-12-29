package org.usd232.robotics.management.apis;

import java.io.Serializable;

/**
 * The request to add an excused absence to a user
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class ExcuseRequest implements Cloneable, Serializable, Comparable<ExcuseRequest>
{
    private static final long serialVersionUID = -7313787289006649445L;
    /**
     * The id of the user to excuse
     * 
     * @since 1.0
     */
    public int     user;
    /**
     * The event to excuse the user from
     * 
     * @since 1.0
     */
    public int     event;
    /**
     * If the absence should be excused
     * 
     * @since 1.0
     */
    public boolean excused;

    @Override
    public ExcuseRequest clone()
    {
        return new ExcuseRequest(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + user;
        result = prime * result + event;
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
        if (!(obj instanceof ExcuseRequest))
        {
            return false;
        }
        ExcuseRequest other = (ExcuseRequest) obj;
        if (user != other.user)
        {
            return false;
        }
        if (event != other.event)
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
    public int compareTo(ExcuseRequest o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("ExcuseRequest [user=%s, event=%s, excused=%s]", user, event, excused);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public ExcuseRequest()
    {
    }

    /**
     * Default constructor
     *
     * @param user
     *            The id of the user to excuse      
     * @param event
     *            The event to excuse the user from      
     * @param excused
     *            If the absence should be excused      
     * @since 1.0
     */
    public ExcuseRequest(int user, int event, boolean excused)
    {
        this.user = user;
        this.event = event;
        this.excused = excused;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public ExcuseRequest(ExcuseRequest obj)
    {
        this(obj.user, obj.event, obj.excused);
    }
}
