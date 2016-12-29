package org.usd232.robotics.management.apis.permissions;

import java.io.Serializable;

/**
 * The permissions the user has
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class Permissions implements Cloneable, Serializable, Comparable<Permissions>
{
    private static final long serialVersionUID = 7926738610588238311L;
    /**
     * The permissions relating to the kiosk
     * 
     * @since 1.0
     */
    public KioskPermissions      kiosk;
    /**
     * The permissions relating to messages
     * 
     * @since 1.0
     */
    public MessagePermissions    message;
    /**
     * The permissions relating to events
     * 
     * @since 1.0
     */
    public EventPermissions      events;
    /**
     * The permissions relating to attendance records
     * 
     * @since 1.0
     */
    public AttendancePermissions attendance;
    /**
     * The permissions relating to users
     * 
     * @since 1.0
     */
    public UserPermissions       users;
    /**
     * The permissions relating to devices
     * 
     * @since 1.0
     */
    public DevicePermissions     devices;
    /**
     * The permissions relating to settings
     * 
     * @since 1.0
     */
    public SettingsPermissions   settings;
    /**
     * The permissions relating to signing in
     * 
     * @since 1.0
     */
    public SignInPermissions     signIn;

    @Override
    public Permissions clone()
    {
        return new Permissions(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((kiosk == null) ? 0 : kiosk.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((events == null) ? 0 : events.hashCode());
        result = prime * result + ((attendance == null) ? 0 : attendance.hashCode());
        result = prime * result + ((users == null) ? 0 : users.hashCode());
        result = prime * result + ((devices == null) ? 0 : devices.hashCode());
        result = prime * result + ((settings == null) ? 0 : settings.hashCode());
        result = prime * result + ((signIn == null) ? 0 : signIn.hashCode());
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
        if (!(obj instanceof Permissions))
        {
            return false;
        }
        Permissions other = (Permissions) obj;
        if (kiosk == null)
        {
            if (other.kiosk != null)
            {
                return false;
            }
        }
        else if (!kiosk.equals(other.kiosk))
        {
            return false;
        }
        if (message == null)
        {
            if (other.message != null)
            {
                return false;
            }
        }
        else if (!message.equals(other.message))
        {
            return false;
        }
        if (events == null)
        {
            if (other.events != null)
            {
                return false;
            }
        }
        else if (!events.equals(other.events))
        {
            return false;
        }
        if (attendance == null)
        {
            if (other.attendance != null)
            {
                return false;
            }
        }
        else if (!attendance.equals(other.attendance))
        {
            return false;
        }
        if (users == null)
        {
            if (other.users != null)
            {
                return false;
            }
        }
        else if (!users.equals(other.users))
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
        if (settings == null)
        {
            if (other.settings != null)
            {
                return false;
            }
        }
        else if (!settings.equals(other.settings))
        {
            return false;
        }
        if (signIn == null)
        {
            if (other.signIn != null)
            {
                return false;
            }
        }
        else if (!signIn.equals(other.signIn))
        {
            return false;
        }
        return true;
   }

    @Override
    public int compareTo(Permissions o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("Permissions [kiosk=%s, message=%s, events=%s, attendance=%s, users=%s, devices=%s, settings=%s, signIn=%s]", kiosk, message, events, attendance, users, devices, settings, signIn);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public Permissions()
    {
    }

    /**
     * Default constructor
     *
     * @param kiosk
     *            The permissions relating to the kiosk      
     * @param message
     *            The permissions relating to messages      
     * @param events
     *            The permissions relating to events      
     * @param attendance
     *            The permissions relating to attendance records      
     * @param users
     *            The permissions relating to users      
     * @param devices
     *            The permissions relating to devices      
     * @param settings
     *            The permissions relating to settings      
     * @param signIn
     *            The permissions relating to signing in      
     * @since 1.0
     */
    public Permissions(KioskPermissions kiosk, MessagePermissions message, EventPermissions events, AttendancePermissions attendance, UserPermissions users, DevicePermissions devices, SettingsPermissions settings, SignInPermissions signIn)
    {
        this.kiosk = kiosk;
        this.message = message;
        this.events = events;
        this.attendance = attendance;
        this.users = users;
        this.devices = devices;
        this.settings = settings;
        this.signIn = signIn;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public Permissions(Permissions obj)
    {
        this(obj.kiosk, obj.message, obj.events, obj.attendance, obj.users, obj.devices, obj.settings, obj.signIn);
    }
}
