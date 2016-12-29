package org.usd232.robotics.management.apis.permissions;

import java.io.Serializable;

/**
 * The permissions relating to users
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class UserPermissions implements Cloneable, Serializable, Comparable<UserPermissions>
{
    private static final long serialVersionUID = 5295643915861349657L;
    /**
     * If the user can view a list of users
     * 
     * @since 1.0
     */
    public boolean view;
    /**
     * If the user can verify users
     * 
     * @since 1.0
     */
    public boolean verify;
    /**
     * If the user can kick members
     * 
     * @since 1.0
     */
    public boolean unverify;

    @Override
    public UserPermissions clone()
    {
        return new UserPermissions(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + Boolean.hashCode(view);
        result = prime * result + Boolean.hashCode(verify);
        result = prime * result + Boolean.hashCode(unverify);
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
        if (!(obj instanceof UserPermissions))
        {
            return false;
        }
        UserPermissions other = (UserPermissions) obj;
        if (view != other.view)
        {
            return false;
        }
        if (verify != other.verify)
        {
            return false;
        }
        if (unverify != other.unverify)
        {
            return false;
        }
        return true;
   }

    @Override
    public int compareTo(UserPermissions o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("UserPermissions [view=%s, verify=%s, unverify=%s]", view, verify, unverify);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public UserPermissions()
    {
    }

    /**
     * Default constructor
     *
     * @param view
     *            If the user can view a list of users      
     * @param verify
     *            If the user can verify users      
     * @param unverify
     *            If the user can kick members      
     * @since 1.0
     */
    public UserPermissions(boolean view, boolean verify, boolean unverify)
    {
        this.view = view;
        this.verify = verify;
        this.unverify = unverify;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public UserPermissions(UserPermissions obj)
    {
        this(obj.view, obj.verify, obj.unverify);
    }
}
