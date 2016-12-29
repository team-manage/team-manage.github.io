package org.usd232.robotics.management.apis.permissions;

import java.io.Serializable;

/**
 * The permissions relating to the kiosk
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class KioskPermissions implements Cloneable, Serializable, Comparable<KioskPermissions>
{
    private static final long serialVersionUID = -5213359363254706127L;
    /**
     * If the user has permission to open the kiosk
     * 
     * @since 1.0
     */
    public boolean open;

    @Override
    public KioskPermissions clone()
    {
        return new KioskPermissions(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + Boolean.hashCode(open);
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
        if (!(obj instanceof KioskPermissions))
        {
            return false;
        }
        KioskPermissions other = (KioskPermissions) obj;
        if (open != other.open)
        {
            return false;
        }
        return true;
   }

    @Override
    public int compareTo(KioskPermissions o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("KioskPermissions [open=%s]", open);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public KioskPermissions()
    {
    }

    /**
     * Default constructor
     *
     * @param open
     *            If the user has permission to open the kiosk      
     * @since 1.0
     */
    public KioskPermissions(boolean open)
    {
        this.open = open;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public KioskPermissions(KioskPermissions obj)
    {
        this(obj.open);
    }
}
