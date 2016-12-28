package org.usd232.robotics.management.server;

import org.usd232.robotics.management.server.routing.GetApi;
import org.usd232.robotics.management.server.routing.PostApi;

public class TestRoutes
{
    @GetApi("/hello")
    public static String hello()
    {
        return "Hello, world!";
    }

    @PostApi("/hello")
    public static String greet(String name)
    {
        return String.format("Hello, %s!", name);
    }
}
