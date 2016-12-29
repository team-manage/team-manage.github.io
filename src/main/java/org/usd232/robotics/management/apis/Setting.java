package org.usd232.robotics.management.apis;

import java.io.Serializable;

/**
 * The model class representing a setting
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class Setting implements Cloneable, Serializable, Comparable<Setting>
{
    private static final long serialVersionUID = -3229469791929279489L;
    /**
     * The name of the setting
     * 
     * @since 1.0
     */
    public String      name;
    /**
     * The type of the setting
     * 
     * @since 1.0
     */
    public SettingType type;
    /**
     * The value of the setting
     * 
     * @since 1.0
     */
    public Object      value;

    @Override
    public Setting clone()
    {
        return new Setting(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        if (!(obj instanceof Setting))
        {
            return false;
        }
        Setting other = (Setting) obj;
        if (name == null)
        {
            if (other.name != null)
            {
                return false;
            }
        }
        else if (!name.equals(other.name))
        {
            return false;
        }
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
    public int compareTo(Setting o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("Setting [name=%s, type=%s, value=%s]", name, type, value);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public Setting()
    {
    }

    /**
     * Default constructor
     *
     * @param name
     *            The name of the setting      
     * @param type
     *            The type of the setting      
     * @param value
     *            The value of the setting      
     * @since 1.0
     */
    public Setting(String name, SettingType type, Object value)
    {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public Setting(Setting obj)
    {
        this(obj.name, obj.type, obj.value);
    }
}
