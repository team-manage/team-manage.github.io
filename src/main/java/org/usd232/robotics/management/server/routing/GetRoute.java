package org.usd232.robotics.management.server.routing;

import java.lang.reflect.Method;
import spark.Request;
import spark.Route;

/**
 * The default route for GET requests
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
class GetRoute extends BaseRoute implements Route
{
    @Override
    protected Object performRequest(Request req) throws Exception
    {
        return method.invoke(null);
    }

    /**
     * Default constructor
     * 
     * @param method
     *            The method that this route represents
     * @since 1.0
     */
    public GetRoute(Method method)
    {
        super(method);
    }
}
