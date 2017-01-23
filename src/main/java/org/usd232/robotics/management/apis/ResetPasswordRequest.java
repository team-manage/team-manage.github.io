package org.usd232.robotics.management.apis;

import java.io.Serializable;

/**
 * The request to reset a user's password using his/her token he/she got from the forgot credentials page
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class ResetPasswordRequest implements Cloneable, Serializable, Comparable<ResetPasswordRequest>
{
    private static final long serialVersionUID = 8651034266347665585L;
    /**
     * The token from the forgot credentials page
     * 
     * @since 1.0
     */
    public String             token;
    /**
     * The new password to set
     * 
     * @since 1.0
     */
    public String             password;

    @Override
    public ResetPasswordRequest clone()
    {
        return new ResetPasswordRequest(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((token == null) ? 0 : token.hashCode());
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
        if (!(obj instanceof ResetPasswordRequest))
        {
            return false;
        }
        ResetPasswordRequest other = (ResetPasswordRequest) obj;
        if (token == null)
        {
            if (other.token != null)
            {
                return false;
            }
        }
        else if (!token.equals(other.token))
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
    public int compareTo(ResetPasswordRequest o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("ResetPasswordRequest [token=%s, password=%s]", token, password);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public ResetPasswordRequest()
    {
    }

    /**
     * Default constructor
     *
     * @param token
     *            The token from the forgot credentials page
     * @param password
     *            The new password to set
     * @since 1.0
     */
    public ResetPasswordRequest(String token, String password)
    {
        this.token = token;
        this.password = password;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public ResetPasswordRequest(ResetPasswordRequest obj)
    {
        this(obj.token, obj.password);
    }
}
