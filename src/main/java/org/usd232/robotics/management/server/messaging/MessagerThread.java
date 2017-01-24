package org.usd232.robotics.management.server.messaging;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.Statement;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.usd232.robotics.management.server.database.Database;

/**
 * The thread that makes sure all of the messages that need to be sent have been sent
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class MessagerThread extends Thread
{
    private static final Logger         LOG          = LogManager.getLogger();
    /**
     * The amount of time to sleep between checking if all messages have been sent
     * 
     * @since 1.0
     */
    private static final TemporalAmount SLEEP_AMOUNT = Duration.ofMinutes(10);

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                try
                {
                    List<Integer> users = new ArrayList<Integer>();
                    try (Statement st = Database.createStatement())
                    {
                        try (ResultSet res = st.executeQuery("SELECT `id` FROM `users`"))
                        {
                            while (res.next())
                            {
                                users.add(res.getInt(1));
                            }
                        }
                    }
                    catch (SQLNonTransientConnectionException ex)
                    {
                        Database.ensureConnected();
                        continue;
                    }
                    for (AbstractMessageReason message : Messages.MESSAGES)
                    {
                        message.update(users);
                    }
                }
                catch (SQLException ex)
                {
                    LOG.catching(ex);
                }
                sleep(Instant.EPOCH.plus(SLEEP_AMOUNT).getEpochSecond() * 1000);
            }
        }
        catch (InterruptedException ex)
        {
            LOG.catching(ex);
        }
    }
}
