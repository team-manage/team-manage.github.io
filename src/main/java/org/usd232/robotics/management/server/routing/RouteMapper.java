package org.usd232.robotics.management.server.routing;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
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
        for (Method method : refl.getMethodsAnnotatedWith(GetApi.class))
        {
            if ((method.getModifiers() & REQUIRED_MODIFIERS) == REQUIRED_MODIFIERS && method.getParameterCount() == 0)
            {
                String path = method.getAnnotation(GetApi.class).value().concat(".json");
                Spark.get(path, new GetRoute(method));
                Spark.options(path, new OptionsRoute());
            }
        }
        for (Method method : refl.getMethodsAnnotatedWith(PostApi.class))
        {
            if ((method.getModifiers() & REQUIRED_MODIFIERS) == REQUIRED_MODIFIERS && method.getParameterCount() == 1)
            {
                String path = method.getAnnotation(PostApi.class).value().concat(".json");
                Spark.post(path, new PostRoute(method));
                Spark.options(path, new OptionsRoute());
            }
        }
    }
}
