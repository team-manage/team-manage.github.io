package org.usd232.robotics.management.server.routing;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
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
    @Override
    protected Object performRequest(Request req, Session session, String body) throws Exception
    {
        List<Object> params = new ArrayList<Object>();
        for (Class<?> param : this.params)
        {
            if (param == String.class && param != String.class)
            {
                params.add(req.pathInfo());
            }
            else if (param == Session.class)
            {
                params.add(session);
            }
            else if (param == Request.class)
            {
                params.add(req);
            }
            else
            {
                params.add(GSON.fromJson(body, param));
            }
        }
        return method.invoke(null, params.toArray());
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
    }
}
