package org.usd232.robotics.management.server.routing;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.usd232.robotics.management.server.session.Session;
import spark.Spark;

/**
 * Maps the routes for all of the api annotations
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class RouteMapper
{
    /**
     * The modifiers that are required for an api function
     * 
     * @since 1.0
     */
    private static final int REQUIRED_MODIFIERS = Modifier.PUBLIC | Modifier.STATIC;

    /**
     * Maps the routes
     * 
     * @since 1.0
     */
    public static void map()
    {
        Reflections refl = new Reflections("org.usd232.robotics.management.server", new MethodAnnotationsScanner());
        search: for (Method method : refl.getMethodsAnnotatedWith(GetApi.class))
        {
            if ((method.getModifiers() & REQUIRED_MODIFIERS) == REQUIRED_MODIFIERS)
            {
                Class<?>[] params = method.getParameterTypes();
                switch (params.length)
                {
                    case 0:
                        break;
                    case 1:
                        if (params[0] == Session.class || params[0] == String.class)
                        {
                            break;
                        }
                        continue search;
                    case 2:
                        if ((params[0] == Session.class && params[1] == String.class)
                                        || (params[0] == String.class && params[1] == Session.class))
                        {
                            break;
                        }
                        continue search;
                    default:
                        continue search;
                }
                String path = method.getAnnotation(GetApi.class).value();
                if (!path.endsWith("*"))
                {
                    path = path.concat(".json");
                }
                Spark.get(path, new GetRoute(method));
                Spark.options(path, new OptionsRoute());
            }
        }
        search: for (Method method : refl.getMethodsAnnotatedWith(PostApi.class))
        {
            if ((method.getModifiers() & REQUIRED_MODIFIERS) == REQUIRED_MODIFIERS)
            {
                Class<?>[] params = method.getParameterTypes();
                switch (params.length)
                {
                    case 1:
                        break;
                    case 2:
                        if (params[0] == Session.class || params[0] == String.class || params[1] == Session.class
                                        || params[1] == String.class)
                        {
                            break;
                        }
                        continue search;
                    case 3:
                        if ((params[0] == Session.class || params[1] == Session.class || params[2] == Session.class)
                                        && (params[0] == String.class || params[1] == String.class
                                                        || params[2] == String.class))
                        {
                            break;
                        }
                        continue search;
                    default:
                        continue search;
                }
                String path = method.getAnnotation(PostApi.class).value();
                if (!path.endsWith("*"))
                {
                    path = path.concat(".json");
                }
                Spark.post(path, new PostRoute(method));
                Spark.options(path, new OptionsRoute());
            }
        }
    }
}
