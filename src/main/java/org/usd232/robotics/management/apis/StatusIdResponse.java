package org.usd232.robotics.management.apis;

import java.io.Serializable;

/**
 * A response that includes a status as well as an id
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class StatusIdResponse implements Cloneable, Serializable, Comparable<StatusIdResponse>
{
    private static final long serialVersionUID = 5308667315029402876L;
    /**
     * If the request was successful
     * 
     * @since 1.0
     */
    public boolean success;
    /**
     * The id to respond with
     * 
     * @since 1.0
     */
    public int     id;

    @Override
    public StatusIdResponse clone()
    {
        return new StatusIdResponse(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + Boolean.hashCode(success);
        result = prime * result + id;
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
        if (!(obj instanceof StatusIdResponse))
        {
            return false;
        }
        StatusIdResponse other = (StatusIdResponse) obj;
        if (success != other.success)
        {
            return false;
        }
        if (id != other.id)
        {
            return false;
        }
        return true;
   }

    @Override
    public int compareTo(StatusIdResponse o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("StatusIdResponse [success=%s, id=%s]", success, id);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public StatusIdResponse()
    {
    }

    /**
     * Default constructor
     *
     * @param success
     *            If the request was successful      
     * @param id
     *            The id to respond with      
     * @since 1.0
     */
    public StatusIdResponse(boolean success, int id)
    {
        this.success = success;
        this.id = id;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public StatusIdResponse(StatusIdResponse obj)
    {
        this(obj.success, obj.id);
    }
}
