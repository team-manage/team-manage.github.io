package org.usd232.robotics.management.apis;

/**
 * The target of a notification message sent by a member
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public enum NotificationTarget
{
    /**
     * The notification should only be sent to that subteam
     * 
     * @since 1.0
     */
    subteam,
    /**
     * The notification should be sent to the entire team
     * 
     * @since 1.0
     */
    team
}
