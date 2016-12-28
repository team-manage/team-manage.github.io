package org.usd232.robotics.management.server.routing;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a method as the endpoint for a GET request
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface GetApi
{
    /**
     * Gets the URL of the endpoint
     * 
     * @return The URL
     * @since 1.0
     */
    String value();
}
