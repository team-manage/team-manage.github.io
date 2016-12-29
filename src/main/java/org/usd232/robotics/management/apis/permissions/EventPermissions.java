package org.usd232.robotics.management.apis.permissions;

import java.io.Serializable;

/**
 * The permissions relating to events
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class EventPermissions implements Cloneable, Serializable, Comparable<EventPermissions>
{
    private static final long serialVersionUID = 3842776982245377222L;
    /**
     * If the user can view a list of all of the events
     * 
     * @since 1.0
     */
    public boolean              view;
    /**
     * If the user can add new events
     * 
     * @since 1.0
     */
    public boolean              add;
    /**
     * How the user can edit events
     * 
     * @since 1.0
     */
    public EventEditPermissions edit;
    /**
     * If the user can remove events
     * 
     * @since 1.0
     */
    public boolean              remove;

    @Override
    public EventPermissions clone()
    {
        return new EventPermissions(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + Boolean.hashCode(view);
        result = prime * result + Boolean.hashCode(add);
        result = prime * result + ((edit == null) ? 0 : edit.hashCode());
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
        if (!(obj instanceof EventPermissions))
        {
            return false;
        }
        EventPermissions other = (EventPermissions) obj;
        if (view != other.view)
        {
            return false;
        }
        if (add != other.add)
        {
            return false;
        }
        if (edit == null)
        {
            if (other.edit != null)
            {
                return false;
            }
        }
        else if (!edit.equals(other.edit))
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
    public int compareTo(EventPermissions o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("EventPermissions [view=%s, add=%s, edit=%s, remove=%s]", view, add, edit, remove);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public EventPermissions()
    {
    }

    /**
     * Default constructor
     *
     * @param view
     *            If the user can view a list of all of the events      
     * @param add
     *            If the user can add new events      
     * @param edit
     *            How the user can edit events      
     * @param remove
     *            If the user can remove events      
     * @since 1.0
     */
    public EventPermissions(boolean view, boolean add, EventEditPermissions edit, boolean remove)
    {
        this.view = view;
        this.add = add;
        this.edit = edit;
        this.remove = remove;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public EventPermissions(EventPermissions obj)
    {
        this(obj.view, obj.add, obj.edit, obj.remove);
    }
}
