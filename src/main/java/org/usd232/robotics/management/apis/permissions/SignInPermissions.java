package org.usd232.robotics.management.apis.permissions;

import java.io.Serializable;

/**
 * The permissions relating to signing in
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class SignInPermissions implements Cloneable, Serializable, Comparable<SignInPermissions>
{
    private static final long serialVersionUID = 166128175541884562L;
    /**
     * If the user can sign in using the kiosk
     * 
     * @since 1.0
     */
    public boolean kisok;
    /**
     * If the user can sign in with a 2D code using the kiosk
     * 
     * @since 1.0
     */
    public boolean code;
    /**
     * If the user can use automatic sign ins
     * 
     * @since 1.0
     */
    public boolean auto;

    @Override
    public SignInPermissions clone()
    {
        return new SignInPermissions(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + Boolean.hashCode(kisok);
        result = prime * result + Boolean.hashCode(code);
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
        if (!(obj instanceof SignInPermissions))
        {
            return false;
        }
        SignInPermissions other = (SignInPermissions) obj;
        if (kisok != other.kisok)
        {
            return false;
        }
        if (code != other.code)
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
    public int compareTo(SignInPermissions o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("SignInPermissions [kisok=%s, code=%s, auto=%s]", kisok, code, auto);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public SignInPermissions()
    {
    }

    /**
     * Default constructor
     *
     * @param kisok
     *            If the user can sign in using the kiosk      
     * @param code
     *            If the user can sign in with a 2D code using the kiosk      
     * @param auto
     *            If the user can use automatic sign ins      
     * @since 1.0
     */
    public SignInPermissions(boolean kisok, boolean code, boolean auto)
    {
        this.kisok = kisok;
        this.code = code;
        this.auto = auto;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public SignInPermissions(SignInPermissions obj)
    {
        this(obj.kisok, obj.code, obj.auto);
    }
}
