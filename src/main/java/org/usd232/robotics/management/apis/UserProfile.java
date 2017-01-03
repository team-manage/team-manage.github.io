package org.usd232.robotics.management.apis;

import java.io.Serializable;
import java.util.List;

/**
 * A model object representing the profile of a user
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class UserProfile implements Cloneable, Serializable, Comparable<UserProfile>
{
    private static final long serialVersionUID = -5725801644441825087L;
    /**
     * The methods of contacting the user
     * 
     * @since 1.0
     */
    public List<UserContact>  contact;
    /**
     * The url to the picture of the user
     * 
     * @since 1.0
     */
    public String             picture;
    /**
     * The pin number for the user to sign in to the kiosk with
     * 
     * @since 1.0
     */
    public int                pin;
    /**
     * The name of the user
     * 
     * @since 1.0
     */
    public String             name;

    @Override
    public UserProfile clone()
    {
        return new UserProfile(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((contact == null) ? 0 : contact.hashCode());
        result = prime * result + ((picture == null) ? 0 : picture.hashCode());
        result = prime * result + pin;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        if (!(obj instanceof UserProfile))
        {
            return false;
        }
        UserProfile other = (UserProfile) obj;
        if (contact == null)
        {
            if (other.contact != null)
            {
                return false;
            }
        }
        else if (!contact.equals(other.contact))
        {
            return false;
        }
        if (picture == null)
        {
            if (other.picture != null)
            {
                return false;
            }
        }
        else if (!picture.equals(other.picture))
        {
            return false;
        }
        if (pin != other.pin)
        {
            return false;
        }
        if (name == null)
        {
            if (other.name != null)
            {
                return false;
            }
        }
        else if (!name.equals(other.name))
        {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(UserProfile o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("UserProfile [contact=%s, picture=%s, pin=%s, name=%s]", contact, picture, pin, name);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public UserProfile()
    {
    }

    /**
     * Default constructor
     *
     * @param contact
     *            The methods of contacting the user
     * @param picture
     *            The url to the picture of the user
     * @param pin
     *            The pin number for the user to sign in to the kiosk with
     * @param name
     *            The name of the user
     * @since 1.0
     */
    public UserProfile(List<UserContact> contact, String picture, int pin, String name)
    {
        this.contact = contact;
        this.picture = picture;
        this.pin = pin;
        this.name = name;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public UserProfile(UserProfile obj)
    {
        this(obj.contact, obj.picture, obj.pin, obj.name);
    }
}
