package org.usd232.robotics.management.server.session.caching;

import java.util.HashMap;
import java.util.Map;
import org.usd232.robotics.management.server.session.Session;
import spark.Route;

/**
 * The class that manages the cache entries
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class CacheManager implements Runnable
{
    /**
     * A map of all of the caches this class is managing
     * 
     * @since 1.0
     */
    private static final Map<Session, Map<Route, CacheEntry>> CACHES = new HashMap<Session, Map<Route, CacheEntry>>();
    /**
     * The session this listener is watching
     * 
     * @since 1.0
     */
    private final Session                                     session;

    /**
     * Handles the caching of a result
     * 
     * @param session
     *            The session that the route was handling for
     * @param route
     *            The route that handled the data
     * @param data
     *            The result of the route
     * @return The information about the cache
     * @since 1.0
     */
    public static CacheData cache(Session session, Route route, Object data)
    {
        if (session == null || route == null || data == null)
        {
            return new CacheData();
        }
        Map<Route, CacheEntry> sessionMap;
        if (CACHES.containsKey(session))
        {
            sessionMap = CACHES.get(session);
        }
        else
        {
            sessionMap = new HashMap<Route, CacheEntry>();
            CACHES.put(session, sessionMap);
            session.addTerminatedListener(new CacheManager(session));
        }
        CacheEntry cache;
        if (sessionMap.containsKey(route))
        {
            cache = sessionMap.get(route);
        }
        else
        {
            cache = new CacheEntry();
            sessionMap.put(route, cache);
        }
        return new CacheData(cache, data);
    }

    @Override
    public void run()
    {
        CACHES.remove(session);
    }

    /**
     * Default constructor
     * 
     * @param session
     *            The session this listener is watching
     * @since 1.0
     */
    private CacheManager(Session session)
    {
        this.session = session;
    }
}
