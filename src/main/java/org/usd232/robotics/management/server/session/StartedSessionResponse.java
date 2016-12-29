package org.usd232.robotics.management.server.session;

/**
 * A response to an api call in which a session is started
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class StartedSessionResponse
{
    /**
     * The new session
     * 
     * @since 1.0
     */
    public final Session session;
    /**
     * The response object to serialize to JSON
     * 
     * @since 1.0
     */
    public final Object  res;

    /**
     * Default constructor
     * 
     * @param res
     *            The response object to serialize to JSON
     * @since 1.0
     */
    public StartedSessionResponse(Object res)
    {
        session = SessionManager.createSession();
        this.res = res;
    }
}
