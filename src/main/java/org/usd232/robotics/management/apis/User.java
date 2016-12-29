package org.usd232.robotics.management.apis;

import java.io.Serializable;

/**
 * The model class representing a user
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class User implements Cloneable, Serializable, Comparable<User>
{
    private static final long serialVersionUID = -7519128167731833678L;
    /**
     * The id of the user
     * 
     * @since 1.0
     */
    public int     id;
    /**
     * The name of the user
     * 
     * @since 1.0
     */
    public String  name;
    /**
     * The url to the picture of the user
     * 
     * @since 1.0
     */
    public String  picture;
    /**
     * If the user has been verified
     * 
     * @since 1.0
     */
    public Boolean verified;
    /**
     * The number of unexcused absences
     * 
     * @since 1.0
     */
    public int     unexcused;
    /**
     * The number of meetings the user has been late to
     * 
     * @since 1.0
     */
    public int     late;

    @Override
    public User clone()
    {
        return new User(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((picture == null) ? 0 : picture.hashCode());
        result = prime * result + ((verified == null) ? 0 : verified.hashCode());
        result = prime * result + unexcused;
        result = prime * result + late;
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
        if (!(obj instanceof User))
        {
            return false;
        }
        User other = (User) obj;
        if (id != other.id)
        {
            return false;
        }
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
        if (picture == null)
        {
            if (other.picture != null)
            {
                return false;
            }
        }
        else if (!picture.equals(other.picture))
        {
            return false;
        }
        if (verified == null)
        {
            if (other.verified != null)
            {
                return false;
            }
        }
        else if (!verified.equals(other.verified))
        {
            return false;
        }
        if (unexcused != other.unexcused)
        {
            return false;
        }
        if (late != other.late)
        {
            return false;
        }
        return true;
   }

    @Override
    public int compareTo(User o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("User [id=%s, name=%s, picture=%s, verified=%s, unexcused=%s, late=%s]", id, name, picture, verified, unexcused, late);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public User()
    {
    }

    /**
     * Default constructor
     *
     * @param id
     *            The id of the user      
     * @param name
     *            The name of the user      
     * @param picture
     *            The url to the picture of the user      
     * @param verified
     *            If the user has been verified      
     * @param unexcused
     *            The number of unexcused absences      
     * @param late
     *            The number of meetings the user has been late to      
     * @since 1.0
     */
    public User(int id, String name, String picture, Boolean verified, int unexcused, int late)
    {
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.verified = verified;
        this.unexcused = unexcused;
        this.late = late;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public User(User obj)
    {
        this(obj.id, obj.name, obj.picture, obj.verified, obj.unexcused, obj.late);
    }
}
