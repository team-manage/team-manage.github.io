package org.usd232.robotics.management.server.session;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The thread that ends sessions
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
class SessionTerminationThread extends Thread
{
    private static final Logger LOG               = LogManager.getLogger();
    /**
     * The maximum time for a session to be inactive for before it is terminated (in milliseconds)
     * 
     * @since 1.0
     */
    static final long           MAX_INACTIVE_TIME = 86400000;              // 1 day

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                long timeout = Long.MAX_VALUE;
                synchronized (SessionManager.SESSIONS)
                {
                    List<Session> expired = new ArrayList<Session>();
                    for (Session session : SessionManager.SESSIONS.values())
                    {
                        long time = session.timeUntilTermination();
                        if (time <= 0)
                        {
                            session.onTermination();
                            expired.add(session);
                        }
                        else
                        {
                            timeout = Math.min(timeout, session.timeUntilTermination());
                        }
                    }
                    SessionManager.SESSIONS.values().removeAll(expired);
                }
                Thread.sleep(timeout);
            }
        }
        catch (InterruptedException ex)
        {
            LOG.catching(ex);
        }
    }
}
