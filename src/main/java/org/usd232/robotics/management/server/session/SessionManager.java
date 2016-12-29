package org.usd232.robotics.management.server.session;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * The class that manages the sessions for the users
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public abstract class SessionManager
{
    /**
     * A map of session ids to session objects
     * 
     * @since 1.0
     */
    static final Map<UUID, Session> SESSIONS = Collections.synchronizedMap(new HashMap<UUID, Session>());
    static
    {
        new SessionTerminationThread().start();
    }

    /**
     * Generates a new session token that is not already tied to an existing session.
     * 
     * @return The token
     * @since 1.0
     */
    private static UUID generateToken()
    {
        UUID uuid;
        do
        {
            uuid = UUID.randomUUID();
        }
        while (SESSIONS.containsKey(uuid));
        return uuid;
    }

    /**
     * Gets the session object from its id
     * 
     * @param uuid
     *            The uuid
     * @return The session object
     * @since 1.0
     */
    public static Session getSession(UUID uuid)
    {
        if (SESSIONS.containsKey(uuid))
        {
            return SESSIONS.get(uuid);
        }
        else
        {
            return null;
        }
    }

    /**
     * Gets the session object from its id
     * 
     * @param uuid
     *            The uuid
     * @return The session object
     * @since 1.0
     */
    public static Session getSession(String uuid)
    {
        try
        {
            return getSession(UUID.fromString(uuid));
        }
        catch (IllegalArgumentException ex)
        {
            return null;
        }
    }

    /**
     * Starts a new session for a user
     * 
     * @return The session object
     * @since 1.0
     */
    public static Session createSession()
    {
        synchronized (SESSIONS)
        {
            UUID uuid = generateToken();
            Session session = new Session(uuid);
            SESSIONS.put(uuid, session);
            return session;
        }
    }
}
