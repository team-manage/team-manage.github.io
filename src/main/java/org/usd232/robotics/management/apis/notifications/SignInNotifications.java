package org.usd232.robotics.management.apis.notifications;

import java.io.Serializable;

/**
 * The notification settings relating to signing in
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class SignInNotifications implements Cloneable, Serializable, Comparable<SignInNotifications>
{
    private static final long serialVersionUID = 1418639791851362002L;
    /**
     * If the member should get a notification for every manual sign in
     * 
     * @since 1.0
     */
    public boolean manual;
    /**
     * If the member should get a notification for every automatic sign in
     * 
     * @since 1.0
     */
    public boolean auto;

    @Override
    public SignInNotifications clone()
    {
        return new SignInNotifications(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + Boolean.hashCode(manual);
        result = prime * result + Boolean.hashCode(auto);
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
        if (!(obj instanceof SignInNotifications))
        {
            return false;
        }
        SignInNotifications other = (SignInNotifications) obj;
        if (manual != other.manual)
        {
            return false;
        }
        if (auto != other.auto)
        {
            return false;
        }
        return true;
   }

    @Override
    public int compareTo(SignInNotifications o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("SignInNotifications [manual=%s, auto=%s]", manual, auto);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public SignInNotifications()
    {
    }

    /**
     * Default constructor
     *
     * @param manual
     *            If the member should get a notification for every manual sign in      
     * @param auto
     *            If the member should get a notification for every automatic sign in      
     * @since 1.0
     */
    public SignInNotifications(boolean manual, boolean auto)
    {
        this.manual = manual;
        this.auto = auto;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public SignInNotifications(SignInNotifications obj)
    {
        this(obj.manual, obj.auto);
    }
}
