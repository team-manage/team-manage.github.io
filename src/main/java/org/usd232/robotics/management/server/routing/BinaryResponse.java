package org.usd232.robotics.management.server.routing;

/**
 * Represents a response to an api call that has a binary output
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class BinaryResponse
{
    /**
     * The mime type of the resource
     * 
     * @since 1.0
     */
    public String mime;
    /**
     * The content of the resource
     * 
     * @since 1.0
     */
    public byte[] data;

    /**
     * Default constructor
     * 
     * @param mime
     *            The mime type of the resource
     * @param data
     *            The content of the resource
     * @since 1.0
     */
    public BinaryResponse(String mime, byte[] data)
    {
        this.mime = mime;
        this.data = data;
    }
}
