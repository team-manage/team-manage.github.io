package org.usd232.robotics.management.apis;

import java.io.Serializable;

/**
 * The request to set a setting
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class SetSettingRequest implements Cloneable, Serializable, Comparable<SetSettingRequest>
{
    private static final long serialVersionUID = 1641084339909221568L;
    /**
     * The key of the setting
     * 
     * @since 1.0
     */
    public String key;
    /**
     * The new value for the setting
     * 
     * @since 1.0
     */
    public String value;

    @Override
    public SetSettingRequest clone()
    {
        return new SetSettingRequest(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
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
        if (!(obj instanceof SetSettingRequest))
        {
            return false;
        }
        SetSettingRequest other = (SetSettingRequest) obj;
        if (key == null)
        {
            if (other.key != null)
            {
                return false;
            }
        }
        else if (!key.equals(other.key))
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
        return true;
   }

    @Override
    public int compareTo(SetSettingRequest o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("SetSettingRequest [key=%s, value=%s]", key, value);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public SetSettingRequest()
    {
    }

    /**
     * Default constructor
     *
     * @param key
     *            The key of the setting      
     * @param value
     *            The new value for the setting      
     * @since 1.0
     */
    public SetSettingRequest(String key, String value)
    {
        this.key = key;
        this.value = value;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public SetSettingRequest(SetSettingRequest obj)
    {
        this(obj.key, obj.value);
    }
}
