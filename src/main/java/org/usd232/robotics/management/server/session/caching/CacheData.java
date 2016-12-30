package org.usd232.robotics.management.server.session.caching;

import java.util.Date;

/**
 * Information about a cached entry
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class CacheData
{
    /**
     * If the server needs to send any content in the response to the client
     * 
     * @since 1.0
     */
    public final boolean sendResponse;
    /**
     * The date the content was last modified (for the Last-Modified header)
     * 
     * @since 1.0
     */
    public final Date    lastModified;

    /**
     * Constructs a {@link CacheData} for something that cannot be cached
     * 
     * @since 1.0
     */
    CacheData()
    {
        sendResponse = true;
        lastModified = new Date();
    }

    /**
     * Default constructor
     * 
     * @param entry
     *            The cache entry
     * @param result
     *            The result of the api
     * @since 1.0
     */
    CacheData(CacheEntry entry, Object result)
    {
        this.sendResponse = entry.update(result);
        this.lastModified = entry.time;
    }
}
