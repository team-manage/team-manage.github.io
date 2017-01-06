package org.usd232.robotics.management.server.apis;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.usd232.robotics.management.apis.Device;
import org.usd232.robotics.management.apis.DeviceRole;
import org.usd232.robotics.management.apis.Infrastructure;
import org.usd232.robotics.management.apis.StatusResponse;
import org.usd232.robotics.management.server.database.Database;
import org.usd232.robotics.management.server.routing.GetApi;
import org.usd232.robotics.management.server.routing.PostApi;
import org.usd232.robotics.management.server.session.RequirePermissions;

/**
 * Apis relating to devices
 * 
 * @author Zach Deibert
 * @since 1.0
 * @version 1.0
 */
public class DeviceApis
{
    /**
     * Gets the infrastructure
     * 
     * @return The infrastructure
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     */
    @GetApi("/infrastructure")
    public static Infrastructure get() throws SQLException
    {
        try (Statement st = Database.createStatement())
        {
            try (ResultSet res = st.executeQuery("SELECT `hostname`, `role`, `version` FROM `devices`"))
            {
                List<Device> devices = new ArrayList<Device>();
                while (res.next())
                {
                    devices.add(new Device(res.getString(1), DeviceRole.valueOf(res.getString(2)), res.getString(3)));
                }
                // TODO find latest version
                return new Infrastructure("1.0", devices);
            }
        }
    }

    /**
     * Updates a device
     * 
     * @param hostname
     *            The hostname of the device
     * @return If it was successful
     * @since 1.0
     * @throws SQLException
     *             If an error occurs while connecting to the database
     */
    @PostApi("/update")
    @RequirePermissions("device.update")
    public static StatusResponse update(String hostname) throws SQLException
    {
        // TODO update
        return new StatusResponse(false);
    }
}
