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
    private static final Map<Session, Map<Route, Map<String, CacheEntry>>> CACHES = new HashMap<Session, Map<Route, Map<String, CacheEntry>>>();
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
     * @param parameter
     *            The content of the POST body
     * @param data
     *            The result of the route
     * @return The information about the cache
     * @since 1.0
     */
    public static CacheData cache(Session session, Route route, String parameter, Object data)
    {
        if (session == null || route == null || parameter == null || data == null)
        {
            return new CacheData();
        }
        Map<Route, Map<String, CacheEntry>> sessionMap;
        if (CACHES.containsKey(session))
        {
            sessionMap = CACHES.get(session);
        }
        else
        {
            sessionMap = new HashMap<Route, Map<String, CacheEntry>>();
            CACHES.put(session, sessionMap);
            session.addTerminatedListener(new CacheManager(session));
        }
        Map<String, CacheEntry> requestCaches;
        if (sessionMap.containsKey(route))
        {
            requestCaches = sessionMap.get(route);
        }
        else
        {
            requestCaches = new HashMap<String, CacheEntry>();
            sessionMap.put(route, requestCaches);
        }
        CacheEntry cache;
        if (requestCaches.containsKey(parameter))
        {
            cache = requestCaches.get(parameter);
        }
        else
        {
            cache = new CacheEntry();
            requestCaches.put(parameter, cache);
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
