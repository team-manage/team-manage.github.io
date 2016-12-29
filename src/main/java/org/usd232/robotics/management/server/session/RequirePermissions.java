package org.usd232.robotics.management.server.session;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a method that requires a certain set of permissions
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequirePermissions
{
    /**
     * The permissions the method requires
     * 
     * @return The permissions the method requires
     * @since 1.0
     */
    String[] value();
}
