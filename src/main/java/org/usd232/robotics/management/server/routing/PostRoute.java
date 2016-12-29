package org.usd232.robotics.management.server.routing;

import java.lang.reflect.Method;
import org.usd232.robotics.management.server.session.Session;
import spark.Request;
import spark.Route;

/**
 * The default route for POST requests
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
class PostRoute extends BaseRoute implements Route
{
    /**
     * If the method wants a session object as a parameter
     * 
     * @since 1.0
     */
    private final boolean  wantsSession;
    /**
     * The class of the model object that is a parameter to the api
     * 
     * @since 1.0
     */
    private final Class<?> parameterClass;

    @Override
    protected Object performRequest(Request req, Session session) throws Exception
    {
        Object deser = GSON.fromJson(req.raw().getReader(), parameterClass);
        return method.invoke(null, wantsSession ? new Object[] { deser, session } : new Object[] { deser });
    }

    /**
     * Default constructor
     * 
     * @param method
     *            The method that this route represents
     * @since 1.0
     */
    public PostRoute(Method method)
    {
        super(method);
        this.parameterClass = method.getParameterTypes()[0];
        wantsSession = method.getParameterCount() == 2;
    }
}
