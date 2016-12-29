package org.usd232.robotics.management.apis.permissions;

import java.io.Serializable;

/**
 * The permissions relating to server devices
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class DevicePermissions implements Cloneable, Serializable, Comparable<DevicePermissions>
{
    private static final long serialVersionUID = 5174587094917419512L;
    /**
     * If the user can add new devices
     * 
     * @since 1.0
     */
    public boolean add;
    /**
     * If the user can update the software on devices
     * 
     * @since 1.0
     */
    public boolean update;
    /**
     * If the user can remove devices
     * 
     * @since 1.0
     */
    public boolean remove;

    @Override
    public DevicePermissions clone()
    {
        return new DevicePermissions(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + Boolean.hashCode(add);
        result = prime * result + Boolean.hashCode(update);
        result = prime * result + Boolean.hashCode(remove);
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
        if (!(obj instanceof DevicePermissions))
        {
            return false;
        }
        DevicePermissions other = (DevicePermissions) obj;
        if (add != other.add)
        {
            return false;
        }
        if (update != other.update)
        {
            return false;
        }
        if (remove != other.remove)
        {
            return false;
        }
        return true;
   }

    @Override
    public int compareTo(DevicePermissions o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("DevicePermissions [add=%s, update=%s, remove=%s]", add, update, remove);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public DevicePermissions()
    {
    }

    /**
     * Default constructor
     *
     * @param add
     *            If the user can add new devices      
     * @param update
     *            If the user can update the software on devices      
     * @param remove
     *            If the user can remove devices      
     * @since 1.0
     */
    public DevicePermissions(boolean add, boolean update, boolean remove)
    {
        this.add = add;
        this.update = update;
        this.remove = remove;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public DevicePermissions(DevicePermissions obj)
    {
        this(obj.add, obj.update, obj.remove);
    }
}
