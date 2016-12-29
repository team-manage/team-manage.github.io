package org.usd232.robotics.management.apis;

import java.io.Serializable;

import org.usd232.robotics.management.apis.permissions.Permissions;

/**
 * The response to the {@link LoginRequest}
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class LoginResponse implements Cloneable, Serializable, Comparable<LoginResponse>
{
    private static final long serialVersionUID = 7256818670375427230L;
    /**
     * The status of the authentication
     * 
     * @since 1.0
     */
    public String      authentication;
    /**
     * The permissions of the user
     * 
     * @since 1.0
     */
    public Permissions permissions;
    /**
     * The profile of the user
     * 
     * @since 1.0
     */
    public UserProfile profile;

    @Override
    public LoginResponse clone()
    {
        return new LoginResponse(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((authentication == null) ? 0 : authentication.hashCode());
        result = prime * result + ((permissions == null) ? 0 : permissions.hashCode());
        result = prime * result + ((profile == null) ? 0 : profile.hashCode());
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
        if (!(obj instanceof LoginResponse))
        {
            return false;
        }
        LoginResponse other = (LoginResponse) obj;
        if (authentication == null)
        {
            if (other.authentication != null)
            {
                return false;
            }
        }
        else if (!authentication.equals(other.authentication))
        {
            return false;
        }
        if (permissions == null)
        {
            if (other.permissions != null)
            {
                return false;
            }
        }
        else if (!permissions.equals(other.permissions))
        {
            return false;
        }
        if (profile == null)
        {
            if (other.profile != null)
            {
                return false;
            }
        }
        else if (!profile.equals(other.profile))
        {
            return false;
        }
        return true;
   }

    @Override
    public int compareTo(LoginResponse o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("LoginResponse [authentication=%s, permissions=%s, profile=%s]", authentication, permissions, profile);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public LoginResponse()
    {
    }

    /**
     * Default constructor
     *
     * @param authentication
     *            The status of the authentication      
     * @param permissions
     *            The permissions of the user      
     * @param profile
     *            The profile of the user      
     * @since 1.0
     */
    public LoginResponse(String authentication, Permissions permissions, UserProfile profile)
    {
        this.authentication = authentication;
        this.permissions = permissions;
        this.profile = profile;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public LoginResponse(LoginResponse obj)
    {
        this(obj.authentication, obj.permissions, obj.profile);
    }
}
