package dao;

import model.MonitorDTO;

import java.util.List;

/**
 * Created by Martha on 8/23/2016.
 */
public interface MonitorDAO {

    /**
     * A SQL query string for creating monitor. Monitor.name
     * parameter needed to be injected under "1" index in query statement
     * using statement.setString(parameter index, values) method.*/
    String CREATE_MONITOR = ""
            + "INSERT INTO monitor (monitor_name)"
            + " VALUES (?)";
    /**
     * A SQL query string for reading monitor_id; name and check_frequency parameters from monitor table.
     * Monitor.id needed to be injected under "1"
     * index in query statement using statement.setString(parameter index, values) method.*/
    String READ_MONITOR_BY_ID = ""
            + "SELECT monitor_id, monitor_name, check_frequency"
            + " FROM monitor"
            + " WHERE monitor_id = ?";

    /**
     * A SQL query string for reading all existing monitors from monitor table.*/
    String READ_MONITORS = ""
            + "SELECT *"
            + " FROM monitor";

    /**
     * A SQL query string for updating monitor with its new frequency.
     * * Monitor.id needed to be injected under "1"
     * index in query statement using statement.setString(parameter index, values) method.*/
    String UPDATE_MONITOR_CHECK_FREQUENCY = ""
            + "UPDATE monitor"
            + " SET check_frequency = ?"
            + " WHERE monitor_id = ?";

    /**
     * Creates new monitor in monitor table.
     * @param monitorDTO new monitor to be created, only name field is used,
     *                    id is autoincrement.
     * @return {@link Integer} Has value if object was created and null otherwise.*/
    Integer createMonitor(MonitorDTO monitorDTO);

    /**
     * Reads all existing monitors from monitor table.
     * @return {@link List<MonitorDTO>} only monitor_id, name and check_frequency fields are initialized with
     * retrieved values.*/
    List<MonitorDTO> readMonitors();

    /**
     * Reads monitor from monitor table by specified id.
     * @param monitorId id of monitor to be retrieved.
     * @return {@link MonitorDTO} only monitor_id, name and check_frequency fields are initialized with
     * retrieved values.*/
    MonitorDTO readMonitorById(Integer monitorId);

    /**
     * Updates specified monitor chexk_frequency field with given value.
     * @param monitorId id of monitor to be retrieved.
     * @param monitorCheckFrequency new check_frequency of monitor
     * @return {@link Boolean} true if query dent successfully null otherwise..*/
    Boolean updateMonitorCheckFrequency(Integer monitorId, Integer monitorCheckFrequency);
}
