package org.usd232.robotics.management.apis;

import java.io.Serializable;

/**
 * The request to register a new user
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class RegisterRequest implements Cloneable, Serializable, Comparable<RegisterRequest>
{
    private static final long serialVersionUID = 1489241816302398718L;
    /**
     * The username of the new user
     * 
     * @since 1.0
     */
    public String username;
    /**
     * The first name of the new user
     * 
     * @since 1.0
     */
    public String fname;
    /**
     * The last name of the new user
     * 
     * @since 1.0
     */
    public String lname;
    /**
     * The password for the new user
     * 
     * @since 1.0
     */
    public String password;
    /**
     * The primary email for the new user
     * 
     * @since 1.0
     */
    public String email;
    /**
     * The secondary email for the new user
     * 
     * @since 1.0
     */
    public String email2;
    /**
     * The phone number for the new user
     * 
     * @since 1.0
     */
    public String phone;
    /**
     * The cellular provider for the new user
     * 
     * @since 1.0
     */
    public String provider;

    @Override
    public RegisterRequest clone()
    {
        return new RegisterRequest(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((fname == null) ? 0 : fname.hashCode());
        result = prime * result + ((lname == null) ? 0 : lname.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((email2 == null) ? 0 : email2.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
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
        if (!(obj instanceof RegisterRequest))
        {
            return false;
        }
        RegisterRequest other = (RegisterRequest) obj;
        if (username == null)
        {
            if (other.username != null)
            {
                return false;
            }
        }
        else if (!username.equals(other.username))
        {
            return false;
        }
        if (fname == null)
        {
            if (other.fname != null)
            {
                return false;
            }
        }
        else if (!fname.equals(other.fname))
        {
            return false;
        }
        if (lname == null)
        {
            if (other.lname != null)
            {
                return false;
            }
        }
        else if (!lname.equals(other.lname))
        {
            return false;
        }
        if (password == null)
        {
            if (other.password != null)
            {
                return false;
            }
        }
        else if (!password.equals(other.password))
        {
            return false;
        }
        if (email == null)
        {
            if (other.email != null)
            {
                return false;
            }
        }
        else if (!email.equals(other.email))
        {
            return false;
        }
        if (email2 == null)
        {
            if (other.email2 != null)
            {
                return false;
            }
        }
        else if (!email2.equals(other.email2))
        {
            return false;
        }
        if (phone == null)
        {
            if (other.phone != null)
            {
                return false;
            }
        }
        else if (!phone.equals(other.phone))
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
    public int compareTo(RegisterRequest o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("RegisterRequest [username=%s, fname=%s, lname=%s, password=%s, email=%s, email2=%s, phone=%s, provider=%s]", username, fname, lname, password, email, email2, phone, provider);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public RegisterRequest()
    {
    }

    /**
     * Default constructor
     *
     * @param username
     *            The username of the new user      
     * @param fname
     *            The first name of the new user      
     * @param lname
     *            The last name of the new user      
     * @param password
     *            The password for the new user      
     * @param email
     *            The primary email for the new user      
     * @param email2
     *            The secondary email for the new user      
     * @param phone
     *            The phone number for the new user      
     * @param provider
     *            The cellular provider for the new user      
     * @since 1.0
     */
    public RegisterRequest(String username, String fname, String lname, String password, String email, String email2, String phone, String provider)
    {
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.email = email;
        this.email2 = email2;
        this.phone = phone;
        this.provider = provider;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public RegisterRequest(RegisterRequest obj)
    {
        this(obj.username, obj.fname, obj.lname, obj.password, obj.email, obj.email2, obj.phone, obj.provider);
    }
}
