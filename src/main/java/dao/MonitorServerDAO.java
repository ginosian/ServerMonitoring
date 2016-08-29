package dao;

import entity.MonitorDTO;
import entity.ServerDTO;

import java.util.List;

/**
 * Created by Martha on 8/28/2016.
 */
public interface MonitorServerDAO {

    /**
     * A SQL query string for creating monitor_server, monitor_id and server_id
     * parameters are needed to be injected under "1" and "2" index in query statement
     * using statement.setString(parameter index, values) method.*/
    String CREATE_MONITOR_SERVER = ""
            + "INSERT INTO monitor_server (monitor_id, server_id)"
            + " VALUES (?, ?)";

    /**
     * A SQL query string for reading server
     * from server table by  corresponding monitor_id from monitor_server table.
     * Server.id needed to be injected under "1"
     * index in query statement using statement.setString(parameter index, values) method.*/
    String READ_SERVER_BY_MONITOR_ID = ""
            + "SELECT *"
            + " FROM server"
            + " WHERE server_id LIKE"
            + " (SELECT server_id "
            + " FROM monitor_server "
            + " WHERE monitor_id = ?)";

    /**
     * A SQL query string for reading monitor
     * from monitor table by corresponding server_id from monitor_server table.
     * Server.id needed to be injected under "1"
     * index in query statement using statement.setString(parameter index, values) method.*/
    String READ_MONITOR_BY_SERVER_ID = ""
            + "SELECT *"
            + " FROM monitor"
            + " WHERE monitor_id LIKE"
            + " (SELECT monitor_id "
            + " FROM monitor_server "
            + " WHERE server_id = ?)";

    /**
     * A SQL query string for updating assigned server for specified monitor.
     * Monitor.id needed to be injected under "1"
     * is_default parameter needed to be injected under "2"
     * Server.id needed to be injected under "3".*/
    String UPDATE_SERVER_IN_MONITOR_SERVER = ""
            + "UPDATE monitor_server"
            + " SET server_id = ?"
            + " WHERE monitor_id = ?";

    /**
     * Creates a record in monitor_server table with given parameters.
     * @param monitor_id id of existing monitor
     * @param server_id id of existing server which is a default for location on which monitor is assigned
     * Parameters needed to be injected under "1" and "2"
     * index in query statement using statement.setString(parameter index, values) method.
     * @return {@link Integer} Has value if object was created and null otherwise.*/
    Integer createMonitorServerCrossRecord(Integer monitor_id, Integer server_id);

    /**
     * Reads servers from server table by specified monitor id.
     * @param monitorId id of monitor.
     * @return {@link List<ServerDTO>} retrieved servers.*/
    List<ServerDTO> readServersByMonitorId(Integer monitorId);

    /**
     * Reads monitors from monitor table by specified server id.
     * @param serverId id of server.
     * @return {@link List<MonitorDTO>} retrieved monitors.*/
    List<MonitorDTO> readMonitorsByServerId(Integer serverId);

    /**
     * Updates server for monitor.
     * @param monitorId id of monitor to be updated.
     * @param serverId id of new server.
     * @return {@link MonitorDTO} retrieved monitor.*/
    Boolean updateServerForMonitor(Integer monitorId, Integer serverId);

}
