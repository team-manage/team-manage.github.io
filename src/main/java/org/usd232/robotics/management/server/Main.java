package org.usd232.robotics.management.server;

import org.usd232.robotics.management.server.routing.RouteMapper;
import spark.Spark;

/**
 * The main class
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public abstract class Main
{
    /**
     * The command-line entry point
     * 
     * @param args
     *            The command-line arguments
     * @since 1.0
     */
    public static void main(String[] args)
    {
        try
        {
            Spark.port(Integer.parseInt(System.getenv("PORT")));
        }
        catch (Exception ex)
        {
        }
        RouteMapper.map();
        Spark.internalServerError("{\"error\":500}");
        Spark.notFound("{\"error\":404}");
        Spark.redirect.get("/", "https://team-manage.github.io/");
    }
}
