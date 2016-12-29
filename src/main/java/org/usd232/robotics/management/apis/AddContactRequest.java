package org.usd232.robotics.management.apis;

import java.io.Serializable;

/**
 * The request to add a new contact method to a user
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class AddContactRequest implements Cloneable, Serializable, Comparable<AddContactRequest>
{
    private static final long serialVersionUID = 7596549069192543137L;
    /**
     * The type of contact
     * 
     * @since 1.0
     */
    public ContactType type;
    /**
     * The email address to send to (if it is an email contact)
     * 
     * @since 1.0
     */
    public String      address;
    /**
     * The phone number to send to (if it is a phone contact)
     * 
     * @since 1.0
     */
    public String      number;
    /**
     * The cellular provider (if it is a phone contact)
     * 
     * @since 1.0
     */
    public String      provider;

    @Override
    public AddContactRequest clone()
    {
        return new AddContactRequest(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((number == null) ? 0 : number.hashCode());
        result = prime * result + ((provider == null) ? 0 : provider.hashCode());
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
        if (!(obj instanceof AddContactRequest))
        {
            return false;
        }
        AddContactRequest other = (AddContactRequest) obj;
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
        if (provider == null)
        {
            if (other.provider != null)
            {
                return false;
            }
        }
        else if (!provider.equals(other.provider))
        {
            return false;
        }
        return true;
   }

    @Override
    public int compareTo(AddContactRequest o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("AddContactRequest [type=%s, address=%s, number=%s, provider=%s]", type, address, number, provider);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public AddContactRequest()
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
     * @param provider
     *            The cellular provider (if it is a phone contact)      
     * @since 1.0
     */
    public AddContactRequest(ContactType type, String address, String number, String provider)
    {
        this.type = type;
        this.address = address;
        this.number = number;
        this.provider = provider;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public AddContactRequest(AddContactRequest obj)
    {
        this(obj.type, obj.address, obj.number, obj.provider);
    }
}
