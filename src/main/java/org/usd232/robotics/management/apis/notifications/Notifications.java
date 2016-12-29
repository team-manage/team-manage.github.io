package org.usd232.robotics.management.apis.notifications;

import java.io.Serializable;

/**
 * The notification settings
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class Notifications implements Cloneable, Serializable, Comparable<Notifications>
{
    private static final long serialVersionUID = -3344197945364484890L;
    /**
     * The notification settings relating to signing in
     * 
     * @since 1.0
     */
    public SignInNotifications  signIn;
    /**
     * If the member should be sent broadcast messages from the team
     * 
     * @since 1.0
     */
    public boolean              team;
    /**
     * The notification settings relating to meetings
     * 
     * @since 1.0
     */
    public MeetingNotifications meetings;

    @Override
    public Notifications clone()
    {
        return new Notifications(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((signIn == null) ? 0 : signIn.hashCode());
        result = prime * result + Boolean.hashCode(team);
        result = prime * result + ((meetings == null) ? 0 : meetings.hashCode());
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
        if (!(obj instanceof Notifications))
        {
            return false;
        }
        Notifications other = (Notifications) obj;
        if (signIn == null)
        {
            if (other.signIn != null)
            {
                return false;
            }
        }
        else if (!signIn.equals(other.signIn))
        {
            return false;
        }
        if (team != other.team)
        {
            return false;
        }
        if (meetings == null)
        {
            if (other.meetings != null)
            {
                return false;
            }
        }
        else if (!meetings.equals(other.meetings))
        {
            return false;
        }
        return true;
   }

    @Override
    public int compareTo(Notifications o)
    {
        return ((Integer) hashCode()).compareTo(o.hashCode());
    }

    @Override
    public String toString()
    {
        return String.format("Notifications [signIn=%s, team=%s, meetings=%s]", signIn, team, meetings);
    }

    /**
     * Nullary constructor
     *
     * @since 1.0
     */
    public Notifications()
    {
    }

    /**
     * Default constructor
     *
     * @param signIn
     *            The notification settings relating to signing in      
     * @param team
     *            If the member should be sent broadcast messages from the team      
     * @param meetings
     *            The notification settings relating to meetings      
     * @since 1.0
     */
    public Notifications(SignInNotifications signIn, boolean team, MeetingNotifications meetings)
    {
        this.signIn = signIn;
        this.team = team;
        this.meetings = meetings;
    }

    /**
     * Copy constructor
     *
     * @param obj
     *            The object to copy from
     * @since 1.0
     */
    public Notifications(Notifications obj)
    {
        this(obj.signIn, obj.team, obj.meetings);
    }
}
