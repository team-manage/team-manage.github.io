package org.usd232.robotics.management.server.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalAmount;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * A class that handles the settings values from the database
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class Settings
{
    /**
     * The maximum time to cache settings for
     * 
     * @since 1.0
     */
    private static final TemporalAmount       MAX_CACHE_TIME = Duration.ofMinutes(10);
    /**
     * The set of keys that have been loaded from the database
     * 
     * @since 1.0
     */
    private static final Set<String>          LOADED_KEYS    = new HashSet<String>();
    /**
     * Maps the key name to the time it was pulled from the database
     * 
     * @since 1.0
     */
    private static final Map<String, Instant> PULL_TIME      = new HashMap<String, Instant>();
    /**
     * The string values of keys loaded from the database
     * 
     * @since 1.0
     */
    private static final Map<String, String>  STRINGS        = new HashMap<String, String>();
    /**
     * The int values of keys loaded from the database
     * 
     * @since 1.0
     */
    private static final Map<String, Integer> INTS           = new HashMap<String, Integer>();
    /**
     * The double values of keys loaded from the database
     * 
     * @since 1.0
     */
    private static final Map<String, Double>  DOUBLES        = new HashMap<String, Double>();

    /**
     * Gets a setting
     * 
     * @param key
     *            The key name
     * @param def
     *            The default value for the setting
     * @param typeName
     *            The name of the type (in the database enum)
     * @param parser
     *            Parses a string to the type of setting
     * @param map
     *            The map containing the cache for this type of setting
     * @return The setting value
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     */
    private static <T> T get(String key, T def, String typeName, Function<String, T> parser, Map<String, T> map)
                    throws SQLException
    {
        if (LOADED_KEYS.contains(key))
        {
            if (map.containsKey(key))
            {
                if (PULL_TIME.get(key).plus(MAX_CACHE_TIME).isBefore(Instant.now()))
                {
                    return map.get(key);
                }
            }
            else
            {
                throw new SQLException("Wrong setting type");
            }
        }
        try (PreparedStatement st = Database.prepareStatement("SELECT `type`, `value` FROM `settings` WHERE `key` = ?"))
        {
            st.setString(1, key);
            try (ResultSet res = st.executeQuery())
            {
                if (res.next())
                {
                    if (res.getString(1).equals(typeName))
                    {
                        LOADED_KEYS.add(key);
                        PULL_TIME.put(key, Instant.now());
                        T val = parser.apply(res.getString(2));
                        map.put(key, val);
                        return val;
                    }
                    else
                    {
                        throw new SQLException("Wrong setting type");
                    }
                }
            }
        }
        try (PreparedStatement st = Database
                        .prepareStatement("INSERT INTO `settings` (`key`, `type`, `value`) VALUES (?, ?, ?)"))
        {
            st.setString(1, key);
            st.setString(2, typeName);
            st.setString(3, def.toString());
            st.execute();
            return def;
        }
    }

    /**
     * Gets a setting as a string
     * 
     * @param key
     *            The key name
     * @param def
     *            The default value for the setting
     * @return The value of the setting
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     */
    public static String getString(String key, String def) throws SQLException
    {
        return get(key, def, "string", Function.identity(), STRINGS);
    }

    /**
     * Gets a setting as an int
     * 
     * @param key
     *            The key name
     * @param def
     *            The default value for the setting
     * @return The value of the setting
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     */
    public static int getInt(String key, int def) throws SQLException
    {
        return get(key, def, "integer", s->Integer.parseInt(s), INTS);
    }

    /**
     * Gets a setting as a double
     * 
     * @param key
     *            The key name
     * @param def
     *            The default value for the setting
     * @return The value of the setting
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     */
    public static double getDouble(String key, double def) throws SQLException
    {
        return get(key, def, "number", s->Double.parseDouble(s), DOUBLES);
    }

    /**
     * Clears a single key from the cache
     * 
     * @param key
     *            The key name
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     */
    public static void modified(String key) throws SQLException
    {
        LOADED_KEYS.remove(key);
    }
}
