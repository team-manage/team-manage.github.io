package org.usd232.robotics.management.server.session;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Represents a session for a user
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class Session
{
    /**
     * The id of the session
     * 
     * @since 1.0
     */
    public final UUID            uuid;
    /**
     * The permissions the user has
     * 
     * @since 1.0
     */
    public Set<String>           permissions;
    /**
     * The last time this object was touched (used to determine when to end a session)
     * 
     * @since 1.0
     */
    private Date                 lastTouch;
    /**
     * A list of the callbacks to run when this session is terminated
     * 
     * @since 1.0
     */
    private final List<Runnable> terminatedCallbacks;

    /**
     * Calls all of the termination callbacks
     * 
     * @since 1.0
     */
    void onTermination()
    {
        for (Runnable r : terminatedCallbacks)
        {
            r.run();
        }
    }

    /**
     * Called after a request reaches the server for this session (used to determine when to end a session)
     * 
     * @since 1.0
     */
    void touch()
    {
        lastTouch = new Date();
    }

    /**
     * Gets the amount of time (in milliseconds) to wait before terminating this session
     * 
     * @return The amount of time (in milliseconds) to wait before terminating this session
     * @since 1.0
     */
    long timeUntilTermination()
    {
        return System.currentTimeMillis() - lastTouch.getTime() - SessionTerminationThread.MAX_INACTIVE_TIME;
    }

    /**
     * Adds a new listener to the terminated callbacks list
     * 
     * @param listener
     *            The listener to call after this session is terminated
     * @since 1.0
     */
    public void addTerminatedListener(Runnable listener)
    {
        terminatedCallbacks.add(listener);
    }

    /**
     * Checks if the user of this session has all of the required permissions
     * 
     * @param permissions
     *            The permissions
     * @return If the user has all of the permissions
     * @since 1.0
     */
    public boolean checkPermissions(String[] permissions)
    {
        if (this.permissions == null)
        {
            return false;
        }
        return this.permissions.containsAll(Arrays.asList(permissions));
    }

    /**
     * Checks if the user of this session has all of the required permissions
     * 
     * @param permissions
     *            The permissions
     * @return If the user has all of the permissions
     * @since 1.0
     */
    public boolean checkPermissions(RequirePermissions permissions)
    {
        if (permissions == null)
        {
            return true;
        }
        return checkPermissions(permissions.value());
    }

    /**
     * Checks if the user of this session has all of the required permissions
     * 
     * @param required
     *            The required permissions
     * @param session
     *            The session to check against
     * @return If the user has all of the permissions
     * @since 1.0
     */
    public static boolean checkPermissions(RequirePermissions required, Session session)
    {
        if (session == null)
        {
            return required == null;
        }
        else
        {
            return session.checkPermissions(required);
        }
    }

    /**
     * Default constructor
     * 
     * @param uuid
     *            The id of the session
     * @since 1.0
     */
    Session(UUID uuid)
    {
        this.uuid = uuid;
        terminatedCallbacks = new ArrayList<Runnable>();
    }
}
