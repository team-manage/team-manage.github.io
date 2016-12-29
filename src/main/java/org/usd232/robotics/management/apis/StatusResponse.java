package org.usd232.robotics.management.apis;

import java.io.Serializable;

/**
 * The response from the server to the client representing when only a status is needed
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class StatusResponse implements Cloneable, Serializable, Comparable<StatusResponse>
{
    private static final long serialVersionUID = 5302476343550971186L;
    /**
     * If the request was successful
     * 
     * @since 1.0
     */
    public boolean success;

    @Override
    public StatusResponse clone()
    {
        return new StatusResponse(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + Boolean.hashCode(success);
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
        if (!(obj instanceof StatusResponse))
        {
            return false;
        }
        StatusResponse other = (StatusResponse) obj;
        if (success != other.success)
        {
            return false;
        }
        return true;
   }

    @Override
    public int compareTo(StatusResponse o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("StatusResponse [success=%s]", success);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public StatusResponse()
    {
    }

    /**
     * Default constructor
     *
     * @param success
     *            If the request was successful      
     * @since 1.0
     */
    public StatusResponse(boolean success)
    {
        this.success = success;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public StatusResponse(StatusResponse obj)
    {
        this(obj.success);
    }
}
