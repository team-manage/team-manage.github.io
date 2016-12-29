package org.usd232.robotics.management.apis;

import java.io.Serializable;

import java.util.List;

/**
 * The model class representing all of the servers connected
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class Infrastructure implements Cloneable, Serializable, Comparable<Infrastructure>
{
    private static final long serialVersionUID = 1457875912694974601L;
    /**
     * The latest version of the software
     * 
     * @since 1.0
     */
    public String       latestVersion;
    /**
     * The list of devices connected
     * 
     * @since 1.0
     */
    public List<Device> devices;

    @Override
    public Infrastructure clone()
    {
        return new Infrastructure(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((latestVersion == null) ? 0 : latestVersion.hashCode());
        result = prime * result + ((devices == null) ? 0 : devices.hashCode());
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
        if (!(obj instanceof Infrastructure))
        {
            return false;
        }
        Infrastructure other = (Infrastructure) obj;
        if (latestVersion == null)
        {
            if (other.latestVersion != null)
            {
                return false;
            }
        }
        else if (!latestVersion.equals(other.latestVersion))
        {
            return false;
        }
        if (devices == null)
        {
            if (other.devices != null)
            {
                return false;
            }
        }
        else if (!devices.equals(other.devices))
        {
            return false;
        }
        return true;
   }

    @Override
    public int compareTo(Infrastructure o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("Infrastructure [latestVersion=%s, devices=%s]", latestVersion, devices);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public Infrastructure()
    {
    }

    /**
     * Default constructor
     *
     * @param latestVersion
     *            The latest version of the software      
     * @param devices
     *            The list of devices connected      
     * @since 1.0
     */
    public Infrastructure(String latestVersion, List<Device> devices)
    {
        this.latestVersion = latestVersion;
        this.devices = devices;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public Infrastructure(Infrastructure obj)
    {
        this(obj.latestVersion, obj.devices);
    }
}
