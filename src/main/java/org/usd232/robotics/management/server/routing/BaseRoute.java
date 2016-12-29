package org.usd232.robotics.management.server.routing;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

abstract class BaseRoute implements Route
{
    private static final Logger LOG  = LogManager.getLogger();
    /**
     * The gson converter
     * 
     * @since 1.0
     */
    protected static final Gson GSON = new Gson();
    /**
     * The method that this route represents
     * 
     * @since 1.0
     */
    protected final Method      method;

    /**
     * Calls the method and returns the result
     * 
     * @param req
     *            The request the client sent
     * @return The result of the request
     * @since 1.0
     * @throws Exception
     *             If an error occurs
     */
    protected abstract Object performRequest(Request req) throws Exception;

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
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            res.header("Last-Modified", dateFormat.format(Calendar.getInstance().getTime()));
            return GSON.toJson(performRequest(req));
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
    }
}