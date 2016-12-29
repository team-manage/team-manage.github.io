package org.usd232.robotics.management.server.database;

import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The configuration settings for establishing a connection to the database
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
class ConnectionConfig extends Properties
{
    private static final long   serialVersionUID = -544782171024072960L;
    private static final Logger LOG              = LogManager.getLogger();

    /**
     * Connects to the database
     * 
     * @return The database connection
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting
     */
    public Connection connect() throws SQLException
    {
        return DriverManager.getConnection(String.format("jdbc:mysql://%s:%s/%s", getProperty("host"),
                        getProperty("port"), getProperty("database")), this);
    }

    /**
     * Loads the configuration from the environmental variables on this system
     * 
     * @since 1.0
     */
    public void loadEnvironment()
    {
        Map<String, String> env = System.getenv();
        if (env.containsKey("DATABASE_PROPERTIES"))
        {
            try (StringReader stream = new StringReader(env.get("DATABASE_PROPERTIES")))
            {
                load(stream);
            }
            catch (IOException ex)
            {
                LOG.catching(ex);
            }
        }
        if (env.containsKey("DATABASE_HOST"))
        {
            setProperty("host", env.get("DATABASE_HOST"));
        }
        if (env.containsKey("DATABASE_PORT"))
        {
            setProperty("port", env.get("DATABASE_PORT"));
        }
        if (env.containsKey("DATABASE_NAME"))
        {
            setProperty("database", env.get("DATABASE_NAME"));
        }
        if (env.containsKey("DATABASE_USERNAME"))
        {
            setProperty("user", env.get("DATABASE_USERNAME"));
        }
        if (env.containsKey("DATABASE_PASSWORD"))
        {
            setProperty("password", env.get("DATABASE_PASSWORD"));
        }
    }

    /**
     * Guesses the configuration
     * 
     * @since 1.0
     */
    public void guessConfig()
    {
        setProperty("host", "localhost");
        setProperty("port", "3306");
        setProperty("database", "team-manage");
        setProperty("user", "root");
        setProperty("password", "root");
    }

    /**
     * Connects to the database
     * 
     * @return The database connection
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting
     */
    public static Connection getConnection() throws SQLException
    {
        ConnectionConfig config = new ConnectionConfig();
        config.guessConfig();
        config.loadEnvironment();
        return config.connect();
    }
}
