package org.usd232.robotics.management.apis.permissions;

import java.io.Serializable;

/**
 * The permissions relating to settings
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class SettingsPermissions implements Cloneable, Serializable, Comparable<SettingsPermissions>
{
    private static final long serialVersionUID = 6826503278160079070L;
    /**
     * If the user can view settings
     * 
     * @since 1.0
     */
    public boolean view;
    /**
     * If the user can edit settings
     * 
     * @since 1.0
     */
    public boolean edit;

    @Override
    public SettingsPermissions clone()
    {
        return new SettingsPermissions(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + Boolean.hashCode(view);
        result = prime * result + Boolean.hashCode(edit);
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
        if (!(obj instanceof SettingsPermissions))
        {
            return false;
        }
        SettingsPermissions other = (SettingsPermissions) obj;
        if (view != other.view)
        {
            return false;
        }
        if (edit != other.edit)
        {
            return false;
        }
        return true;
   }

    @Override
    public int compareTo(SettingsPermissions o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("SettingsPermissions [view=%s, edit=%s]", view, edit);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public SettingsPermissions()
    {
    }

    /**
     * Default constructor
     *
     * @param view
     *            If the user can view settings      
     * @param edit
     *            If the user can edit settings      
     * @since 1.0
     */
    public SettingsPermissions(boolean view, boolean edit)
    {
        this.view = view;
        this.edit = edit;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public SettingsPermissions(SettingsPermissions obj)
    {
        this(obj.view, obj.edit);
    }
}
