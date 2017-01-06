package org.usd232.robotics.management.server.routing;

import java.io.BufferedReader;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.usd232.robotics.management.server.session.RequirePermissions;
import org.usd232.robotics.management.server.session.Session;
import org.usd232.robotics.management.server.session.SessionManager;
import org.usd232.robotics.management.server.session.StartedSessionResponse;
import org.usd232.robotics.management.server.session.caching.CacheData;
import org.usd232.robotics.management.server.session.caching.CacheManager;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

abstract class BaseRoute implements Route
{
    private static final Logger           LOG         = LogManager.getLogger();
    /**
     * The gson converter
     * 
     * @since 1.0
     */
    protected static final Gson           GSON        = new Gson();
    /**
     * The format for the date in the Last-Modified header
     * 
     * @since 1.0
     */
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
    /**
     * The method that this route represents
     * 
     * @since 1.0
     */
    protected final Method                method;
    /**
     * An array of all of the parameter types of the method
     * 
     * @since 1.0
     */
    protected final Class<?>[]            params;
    /**
     * The permissions the method requires
     * 
     * @since 1.0
     */
    private final RequirePermissions      permissions;
    static
    {
        DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    /**
     * Calls the method and returns the result
     * 
     * @param req
     *            The request the client sent
     * @param session
     *            The session the client is in
     * @param body
     *            The body of the request
     * @return The result of the request
     * @since 1.0
     * @throws Exception
     *             If an error occurs
     */
    protected abstract Object performRequest(Request req, Session session, String body) throws Exception;

    /**
     * Adds the CORS headers
     * 
     * @param req
     *            The request
     * @param res
     *            The response
     * @since 1.0
     * @throws Exception
     *             If an error occurs
     */
    protected void handleCORS(Request req, Response res) throws Exception
    {
        res.header("Access-Control-Allow-Origin", "*");
        res.header("Access-Control-Expose-Headers", "X-Session-Token,Last-Modified");
        res.header("Access-Control-Max-Age", "600");
        res.header("Access-Control-Allow-Methods", "GET,POST");
        res.header("Access-Control-Allow-Headers",
                        "DNT,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,X-Session-Token");
    }

    @Override
    public Object handle(Request req, Response res) throws Exception
    {
        try
        {
            handleCORS(req, res);
            if (req.requestMethod().equals("OPTIONS"))
            {
                return "";
            }
            Session session = SessionManager.getSession(req.headers("X-Session-Token"));
            if (!Session.checkPermissions(permissions, session))
            {
                LOG.catching(new IllegalAccessException("The user does not have permission to access this api"));
                res.status(403);
                return "{\"error\":403}";
            }
            String bodyStr;
            try (BufferedReader reader = req.raw().getReader())
            {
                StringBuilder body = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null)
                {
                    body.append(line);
                }
                bodyStr = body.toString();
            }
            Object result = performRequest(req, session, bodyStr);
            if (result instanceof StartedSessionResponse)
            {
                res.header("X-Session-Token", ((StartedSessionResponse) result).session.uuid.toString());
                result = ((StartedSessionResponse) result).res;
            }
            CacheData cache = CacheManager.cache(session, this, bodyStr, result);
            if (!cache.sendResponse)
            {
                result = new Object();
            }
            res.header("Last-Modified", DATE_FORMAT.format(cache.lastModified));
            if (result instanceof BinaryResponse)
            {
                res.header("Content-Type", ((BinaryResponse) result).mime);
                return ((BinaryResponse) result).data;
            }
            else
            {
                return GSON.toJson(result);
            }
        }
        catch (Exception ex)
        {
            LOG.catching(ex);
            throw ex;
        }
    }

    /**
     * Default constructor
     * 
     * @param method
     *            The method that this route represents
     * @since 1.0
     */
    protected BaseRoute(Method method)
    {
        this.method = method;
        if (method == null)
        {
            permissions = null;
            params = null;
        }
        else
        {
            permissions = method.getAnnotation(RequirePermissions.class);
            params = method.getParameterTypes();
        }
    }
}