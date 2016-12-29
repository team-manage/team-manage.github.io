package org.usd232.robotics.management.apis;

import java.io.Serializable;

import org.usd232.robotics.management.apis.notifications.Notifications;

/**
 * The request to change the notification settings for a contact
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class EditContactRequest implements Cloneable, Serializable, Comparable<EditContactRequest>
{
    private static final long serialVersionUID = -7777014630557305950L;
    /**
     * The type of contact
     * 
     * @since 1.0
     */
    public ContactType   type;
    /**
     * The email address to send to (if it is an email contact)
     * 
     * @since 1.0
     */
    public String        address;
    /**
     * The phone number to send to (if it is a phone contact)
     * 
     * @since 1.0
     */
    public String        number;
    /**
     * The notification settings for this contact
     * 
     * @since 1.0
     */
    public Notifications notifications;

    @Override
    public EditContactRequest clone()
    {
        return new EditContactRequest(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((number == null) ? 0 : number.hashCode());
        result = prime * result + ((notifications == null) ? 0 : notifications.hashCode());
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
        if (!(obj instanceof EditContactRequest))
        {
            return false;
        }
        EditContactRequest other = (EditContactRequest) obj;
        if (type == null)
        {
            if (other.type != null)
            {
                return false;
            }
        }
        else if (!type.equals(other.type))
        {
            return false;
        }
        if (address == null)
        {
            if (other.address != null)
            {
                return false;
            }
        }
        else if (!address.equals(other.address))
        {
            return false;
        }
        if (number == null)
        {
            if (other.number != null)
            {
                return false;
            }
        }
        else if (!number.equals(other.number))
        {
            return false;
        }
        if (notifications == null)
        {
            if (other.notifications != null)
            {
                return false;
            }
        }
        else if (!notifications.equals(other.notifications))
        {
            return false;
        }
        return true;
   }

    @Override
    public int compareTo(EditContactRequest o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("EditContactRequest [type=%s, address=%s, number=%s, notifications=%s]", type, address, number, notifications);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public EditContactRequest()
    {
    }

    /**
     * Default constructor
     *
     * @param type
     *            The type of contact      
     * @param address
     *            The email address to send to (if it is an email contact)      
     * @param number
     *            The phone number to send to (if it is a phone contact)      
     * @param notifications
     *            The notification settings for this contact      
     * @since 1.0
     */
    public EditContactRequest(ContactType type, String address, String number, Notifications notifications)
    {
        this.type = type;
        this.address = address;
        this.number = number;
        this.notifications = notifications;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public EditContactRequest(EditContactRequest obj)
    {
        this(obj.type, obj.address, obj.number, obj.notifications);
    }
}
