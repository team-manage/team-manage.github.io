package org.usd232.robotics.management.server.messaging;

import org.usd232.robotics.management.server.messaging.messages.ForgotPasswordMessage;
import org.usd232.robotics.management.server.messaging.messages.ForgotUsernameMessage;
import org.usd232.robotics.management.server.messaging.messages.RegisterMessage;
import org.usd232.robotics.management.server.messaging.messages.UnverifiedMessage;
import org.usd232.robotics.management.server.messaging.messages.VerifiedMessage;

/**
 * A class containing all of the message ids and instances
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class Messages
{
    /**
     * The id of the register message
     * 
     * @since 1.0
     */
    public static final int                     REGISTER_MESSAGE_ID     = 1;
    /**
     * The register message
     * 
     * @since 1.0
     */
    public static final RegisterMessage         REGISTER_MESSAGE        = new RegisterMessage();
    /**
     * The id of the forgot password message
     * 
     * @since 1.0
     */
    public static final int                     FORGOT_PASSWORD_ID      = 2;
    /**
     * The forgot password message
     * 
     * @since 1.0
     */
    public static final ForgotPasswordMessage   FORGOT_PASSWORD_MESSAGE = new ForgotPasswordMessage();
    /**
     * The id of the forgot username message
     * 
     * @since 1.0
     */
    public static final int                     FORGOT_USERNAME_ID      = 3;
    /**
     * The forgot username message
     * 
     * @since 1.0
     */
    public static final ForgotUsernameMessage   FORGOT_USERNAME_MESSAGE = new ForgotUsernameMessage();
    /**
     * The id of the verified message
     * 
     * @since 1.0
     */
    public static final int                     VERIFIED_ID             = 4;
    /**
     * The verified message
     * 
     * @since 1.0
     */
    public static final VerifiedMessage         VERIFIED_MESSAGE        = new VerifiedMessage();
    /**
     * The id of the unverified message
     * 
     * @since 1.0
     */
    public static final int                     UNVERIFIED_ID           = 5;
    /**
     * The unverified message
     * 
     * @since 1.0
     */
    public static final UnverifiedMessage       UNVERIFIED_MESSAGE      = new UnverifiedMessage();
    /**
     * An array of all of the messages
     * 
     * @since 1.0
     */
    public static final AbstractMessageReason[] MESSAGES                = { REGISTER_MESSAGE, FORGOT_PASSWORD_MESSAGE,
                    FORGOT_USERNAME_MESSAGE, VERIFIED_MESSAGE, UNVERIFIED_MESSAGE };
}
