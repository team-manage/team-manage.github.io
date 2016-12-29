package org.usd232.robotics.management.apis;

import java.io.Serializable;

/**
 * The request for the client to log in to the server with
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class LoginRequest implements Cloneable, Serializable, Comparable<LoginRequest>
{
    private static final long serialVersionUID = -4999383020309377890L;
    /**
     * The username of the user
     * 
     * @since 1.0
     */
    public String             username;
    /**
     * The password of the user
     * 
     * @since 1.0
     */
    public String             password;

    @Override
    public LoginRequest clone()
    {
        return new LoginRequest(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
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
        if (!(obj instanceof LoginRequest))
        {
            return false;
        }
        LoginRequest other = (LoginRequest) obj;
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
        return true;
   }

    @Override
    public int compareTo(LoginRequest o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("LoginRequest [username=%s, password=%s]", username, password);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public LoginRequest()
    {
    }

    /**
     * Default constructor
     *
     * @param username
     *            The username of the user      
     * @param password
     *            The password of the user      
     * @since 1.0
     */
    public LoginRequest(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public LoginRequest(LoginRequest obj)
    {
        this(obj.username, obj.password);
    }
}
