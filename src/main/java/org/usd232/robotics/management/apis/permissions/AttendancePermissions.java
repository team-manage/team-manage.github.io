package org.usd232.robotics.management.apis.permissions;

import java.io.Serializable;

/**
 * The permissions relating to attendance records
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class AttendancePermissions implements Cloneable, Serializable, Comparable<AttendancePermissions>
{
    private static final long serialVersionUID = 5289201128626483161L;
    /**
     * If the user can view attendance records
     * 
     * @since 1.0
     */
    public boolean view;
    /**
     * If the user can modify attendance records
     * 
     * @since 1.0
     */
    public boolean modify;
    /**
     * If the user can excuse absences
     * 
     * @since 1.0
     */
    public boolean excuse;

    @Override
    public AttendancePermissions clone()
    {
        return new AttendancePermissions(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + Boolean.hashCode(view);
        result = prime * result + Boolean.hashCode(modify);
        result = prime * result + Boolean.hashCode(excuse);
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
        if (!(obj instanceof AttendancePermissions))
        {
            return false;
        }
        AttendancePermissions other = (AttendancePermissions) obj;
        if (view != other.view)
        {
            return false;
        }
        if (modify != other.modify)
        {
            return false;
        }
        if (excuse != other.excuse)
        {
            return false;
        }
        return true;
   }

    @Override
    public int compareTo(AttendancePermissions o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("AttendancePermissions [view=%s, modify=%s, excuse=%s]", view, modify, excuse);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public AttendancePermissions()
    {
    }

    /**
     * Default constructor
     *
     * @param view
     *            If the user can view attendance records      
     * @param modify
     *            If the user can modify attendance records      
     * @param excuse
     *            If the user can excuse absences      
     * @since 1.0
     */
    public AttendancePermissions(boolean view, boolean modify, boolean excuse)
    {
        this.view = view;
        this.modify = modify;
        this.excuse = excuse;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public AttendancePermissions(AttendancePermissions obj)
    {
        this(obj.view, obj.modify, obj.excuse);
    }
}
