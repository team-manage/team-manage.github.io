package org.usd232.robotics.management.server.session.caching;

import java.util.Date;

/**
 * An entry in a session's cache
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
class CacheEntry
{
    /**
     * The result of the route that was run
     * 
     * @since 1.0
     */
    public Object result;
    /**
     * The time the result was created
     * 
     * @since 1.0
     */
    public Date   time;

    /**
     * Updates the entry with a new result
     * 
     * @param result
     *            The new result
     * @return If the cache was updated
     * @since 1.0
     */
    public boolean update(Object result)
    {
        if (this.result == null || !this.result.equals(result))
        {
            this.result = result;
            time = new Date();
            return true;
        }
        return false;
    }
}
