package org.usd232.robotics.management.server.routing;

import java.lang.reflect.Method;
import org.usd232.robotics.management.server.session.Session;
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
    /**
     * If the method wants a session object as a parameter
     * 
     * @since 1.0
     */
    private final boolean wantsSession;

    @Override
    protected Object performRequest(Request req, Session session) throws Exception
    {
        return method.invoke(null, wantsSession ? new Object[] { session } : new Object[0]);
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
        wantsSession = method.getParameterCount() == 1;
    }
}
