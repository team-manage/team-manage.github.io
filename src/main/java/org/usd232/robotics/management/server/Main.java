package org.usd232.robotics.management.server;

import spark.Spark;

public abstract class Main
{
    public static void main(String[] args)
    {
        try
        {
            Spark.port(Integer.parseInt(System.getenv("PORT")));
        }
        catch (Exception ex)
        {
        }
        Spark.get("/hello", (req, res)->"Hello, world!");
    }
}
