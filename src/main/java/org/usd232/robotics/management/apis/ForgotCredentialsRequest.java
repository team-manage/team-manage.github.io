package org.usd232.robotics.management.apis;

import java.io.Serializable;

/**
 * The request to get a notification containing a user's credentials because the user forgot them.
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class ForgotCredentialsRequest implements Cloneable, Serializable, Comparable<ForgotCredentialsRequest>
{
    private static final long   serialVersionUID = -4145847552098991845L;
    /**
     * The type of credential the user still knows
     * 
     * @since 1.0
     */
    public KnownCredentialType  known;
    /**
     * The credential the user still knows
     * 
     * @since 1.0
     */
    public String               value;
    /**
     * The type of credential the user has forgotten
     * 
     * @since 1.0
     */
    public ForgotCredentialType forgot;

    @Override
    public ForgotCredentialsRequest clone()
    {
        return new ForgotCredentialsRequest(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((known == null) ? 0 : known.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        result = prime * result + ((forgot == null) ? 0 : forgot.hashCode());
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
        if (!(obj instanceof ForgotCredentialsRequest))
        {
            return false;
        }
        ForgotCredentialsRequest other = (ForgotCredentialsRequest) obj;
        if (known == null)
        {
            if (other.known != null)
            {
                return false;
            }
        }
        else if (!known.equals(other.known))
        {
            return false;
        }
        if (value == null)
        {
            if (other.value != null)
            {
                return false;
            }
        }
        else if (!value.equals(other.value))
        {
            return false;
        }
        if (forgot == null)
        {
            if (other.forgot != null)
            {
                return false;
            }
        }
        else if (!forgot.equals(other.forgot))
        {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(ForgotCredentialsRequest o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("ForgotCredentialsRequest [known=%s, value=%s, forgot=%s]", known, value, forgot);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public ForgotCredentialsRequest()
    {
    }

    /**
     * Default constructor
     *
     * @param known
     *            The type of credential the user still knows
     * @param value
     *            The credential the user still knows
     * @param forgot
     *            The type of credential the user has forgotten
     * @since 1.0
     */
    public ForgotCredentialsRequest(KnownCredentialType known, String value, ForgotCredentialType forgot)
    {
        this.known = known;
        this.value = value;
        this.forgot = forgot;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public ForgotCredentialsRequest(ForgotCredentialsRequest obj)
    {
        this(obj.known, obj.value, obj.forgot);
    }
}
