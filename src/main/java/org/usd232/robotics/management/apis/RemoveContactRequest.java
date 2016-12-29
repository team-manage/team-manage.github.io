package org.usd232.robotics.management.apis;

import java.io.Serializable;

/**
 * The request to remove a contact
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class RemoveContactRequest implements Cloneable, Serializable, Comparable<RemoveContactRequest>
{
    private static final long serialVersionUID = -2631989572117725964L;
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

    @Override
    public RemoveContactRequest clone()
    {
        return new RemoveContactRequest(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((number == null) ? 0 : number.hashCode());
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
        if (!(obj instanceof RemoveContactRequest))
        {
            return false;
        }
        RemoveContactRequest other = (RemoveContactRequest) obj;
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
        return true;
   }

    @Override
    public int compareTo(RemoveContactRequest o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("RemoveContactRequest [type=%s, address=%s, number=%s]", type, address, number);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public RemoveContactRequest()
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
     * @since 1.0
     */
    public RemoveContactRequest(ContactType type, String address, String number)
    {
        this.type = type;
        this.address = address;
        this.number = number;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public RemoveContactRequest(RemoveContactRequest obj)
    {
        this(obj.type, obj.address, obj.number);
    }
}
