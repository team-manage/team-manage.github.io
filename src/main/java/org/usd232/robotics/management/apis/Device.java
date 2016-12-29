package org.usd232.robotics.management.apis;

import java.io.Serializable;

/**
 * The model class for a connected device
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class Device implements Cloneable, Serializable, Comparable<Device>
{
    private static final long serialVersionUID = -1335894325387738516L;
    /**
     * The host name of the device
     * 
     * @since 1.0
     */
    public String     hostname;
    /**
     * The role the device has
     * 
     * @since 1.0
     */
    public DeviceRole role;
    /**
     * The version of the software the device is running
     * 
     * @since 1.0
     */
    public String     version;

    @Override
    public Device clone()
    {
        return new Device(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((hostname == null) ? 0 : hostname.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
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
        if (!(obj instanceof Device))
        {
            return false;
        }
        Device other = (Device) obj;
        if (hostname == null)
        {
            if (other.hostname != null)
            {
                return false;
            }
        }
        else if (!hostname.equals(other.hostname))
        {
            return false;
        }
        if (role == null)
        {
            if (other.role != null)
            {
                return false;
            }
        }
        else if (!role.equals(other.role))
        {
            return false;
        }
        if (version == null)
        {
            if (other.version != null)
            {
                return false;
            }
        }
        else if (!version.equals(other.version))
        {
            return false;
        }
        return true;
   }

    @Override
    public int compareTo(Device o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("Device [hostname=%s, role=%s, version=%s]", hostname, role, version);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public Device()
    {
    }

    /**
     * Default constructor
     *
     * @param hostname
     *            The host name of the device      
     * @param role
     *            The role the device has      
     * @param version
     *            The version of the software the device is running      
     * @since 1.0
     */
    public Device(String hostname, DeviceRole role, String version)
    {
        this.hostname = hostname;
        this.role = role;
        this.version = version;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public Device(Device obj)
    {
        this(obj.hostname, obj.role, obj.version);
    }
}
