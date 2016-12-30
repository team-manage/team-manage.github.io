package org.usd232.robotics.management.server.apis;

import org.usd232.robotics.management.server.routing.GetApi;

/**
 * Contains the api that the client uses to detect if it is using a mock implementation of the apis
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class MockDetection
{
    /**
     * The api that the client uses to detect if it is using a mock implementation of the apis
     * 
     * @return false
     * @since 1.0
     */
    @GetApi("/isMock")
    public static boolean isMock()
    {
        return false;
    }
}
