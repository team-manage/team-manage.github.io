package org.usd232.robotics.management.server.database.migrations;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.usd232.robotics.management.server.database.Database;

/**
 * Handles the migrations to the database
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public abstract class DatabaseMigrator
{
    private static final Logger LOG = LogManager.getLogger();

    /**
     * Runs the migrations on the database
     * 
     * @since 1.0
     * @throws IOException
     *             If an I/O error occurs
     * @throws SQLException
     *             If a database error occurs
     */
    public static void runMigrations() throws IOException, SQLException
    {
        while (true)
        {
            String currentVersion = null;
            try
            {
                try (Statement st = Database.createStatement())
                {
                    try (ResultSet res = st
                                    .executeQuery("SELECT `value` FROM `settings` WHERE `key` = 'database.version'"))
                    {
                        if (res.next())
                        {
                            currentVersion = res.getString(1);
                        }
                    }
                }
            }
            catch (SQLException ex)
            {
                LOG.catching(ex);
            }
            try (InputStream stream = DatabaseMigrator.class
                            .getResourceAsStream(String.format("%s.sql", currentVersion)))
            {
                if (stream == null)
                {
                    return;
                }
                StringBuilder builder = new StringBuilder();
                try (Scanner scan = new Scanner(stream))
                {
                    while (scan.hasNextLine())
                    {
                        builder.append(scan.nextLine());
                    }
                }
                for (String sql : builder.toString().split(";"))
                {
                    if (!sql.trim().isEmpty())
                    {
                        try (Statement st = Database.createStatement())
                        {
                            st.execute(sql);
                        }
                    }
                }
            }
        }
    }
}
