package dao;

import entity.LocationDTO;
import entity.ServerDTO;

import java.util.List;

/**
 * Created by Martha on 8/23/2016.
 */
public interface ServerDAO {

    /**
     * A SQL query string for creating server. Server.name
     * parameter needed to be injected under "1" index in query statement
     * using statement.setString(parameter index, values) method.*/
    String CREATE_SERVER = ""
            + "INSERT INTO server (server_name)"
            + " VALUES (?)";
    /**
     * A SQL query string for reading server_id; name and is_default parameters from server table.
     * Server.id needed to be injected under "1"
     * index in query statement using statement.setString(parameter index, values) method.*/
    String READ_SERVER_BY_ID = ""
            + "SELECT server_id, server_name, is_default"
            + " FROM server"
            + " WHERE server_id = ?";

    /**
     * A SQL query string for reading server_id; name and is_default parameters from server table.
     * Where server has the lowst density within location. But actually picks random server.
     * Location.id needed to be injected under "1"
     * index in query statement using statement.setString(parameter index, values) method.*/
    String READ_SERVER_BY_LOWEST_DENSITY = ""
            + "SELECT server_id, server_name, is_default"
            + " FROM server"
            + " WHERE location_id = ?"
            + " ORDER BY RAND() LIMIT 1";

    String READ_SERVER_BY_NAME = ""
            + "SELECT server_id, server_name, is_default"
            + " FROM server"
            + " WHERE server_name = ?";

    /**
     * A SQL query string for reading all existing servers from server table.*/
    String READ_SERVERS = ""
            + "SELECT *"
            + " FROM server";

    /**
     * A SQL query string for reading all existing servers from server table.*/
    String READ_SERVERS_BY_LOCATION_ID = ""
            + "SELECT *"
            + " FROM server"
            + " WHERE location_id = ?";

    /**
     * A SQL query string for updating server with its location and is_default flag.
     * Location.id needed to be injected under "1"
     * is_default parameter needed to be injected under "2"
     * Server.id needed to be injected under "3".*/
    String UPDATE_SERVER_WITH_LOCATION_AND_FLAG = ""
            + "UPDATE server"
            + " SET location_id = ?, is_default = ?"
            + " WHERE server_id = ?";

    /**
     * A SQL query string for updating server with its flag.
     * is_default needed to be injected under "1"
     * server.id parameter needed to be injected under "2".*/
    String UPDATE_SERVER_WITH_FLAG = ""
            + "UPDATE server"
            + " SET is_default = ?"
            + " WHERE server_id = ?";

    /**
     * A SQL query string for reading server_id; name and is_default parameters from server table.
     * Server.id needed to be injected under "1"
     * index in query statement using statement.setString(parameter index, values) method.*/
    String READ_LOCATION_BY_SERVER_ID = ""
            + "SELECT location_id, location_name, addr"
            + " FROM location"
            + " WHERE location_id LIKE"
            + " (SELECT location_id "
            + " FROM server "
            + " WHERE server_id = ?)";

    /**
     * A SQL query string for reading server_id; name and is_default parameters from server table.
     * Location.id needed to be injected under "1"
     * index in query statement using statement.setString(parameter index, values) method.*/
    String READ_DEFAULT_SERVER_IN_LOCATION = ""
            + "SELECT server_id, server_name, is_default"
            + " FROM server"
            + " WHERE location_id = ?"
            + " AND is_default = 1";

    /**
     * Creates new server in server table.
     * @param serverDTO new server to be created, only name field is used,
     *                    id is autoincrement.
     * @return {@link Integer} Has value if object was created and null otherwise.*/
    Integer createServer(ServerDTO serverDTO);

    /**
     * Reads all existing servers from server table.
     * @return {@link List <ServerDTO>} only server_id, name, location_id and flag fields are initialized with
     * retrieved values.*/
    List<ServerDTO> readServers();

    /**
     * Reads servers within same specified location.
     * @return {@link List <ServerDTO>} only server_id, name, location_id and flag fields are initialized with
     * retrieved values.*/
    List<ServerDTO> readServersWithinLocation(Integer location_id);

    /**
     * Reads server from server table by specified id.
     * @param serverId id of server to be retrieved.
     * @return {@link ServerDTO} only server_id, name, location_id and flag fields are initialized with
     * retrieved values.*/
    ServerDTO readServerById(Integer serverId);

    ServerDTO readServerByName(String server_name);

    /**
     * Reads default server from server table by specified location.
     * @param location_id id of location to look in.
     * @return {@link Integer}  id of default server*/
    ServerDTO readDefaultServerWithinLocation(Integer location_id);

    /**
     * Reads location id from specified server.
     * @param serverId id of server to be retrieved.
     * @return {@link LocationDTO} Location where specified server is situated*/
    LocationDTO readLocationIdByServerId(Integer serverId);

    /**
     * Reads server with lowest density, actualy picks random server within
     * location as a server with lowest density.
     * @param location_id id of server to be retrieved.
     * @return {@link ServerDTO} Server with lowest density*/
    ServerDTO readServerWithLowestDensity(Integer location_id);

    /**
     * Updates specified server location_id and flag fields with given values.
     * @param serverId id of server to be retrieved.
     * @param location_id id of location for server,
     * @param is_default flag indicating if server is default for the location
     * @return {@link Boolean} true if query dent successfully false otherwise..*/
    Boolean updateServerWithLocationAndFlag(Integer serverId, Integer location_id, Boolean is_default);

    /**
     * Updates specified server location_id and flag fields with given values.
     * @param serverId id of server to be retrieved.
     * @param is_default flag indicating if server is default for the location
     * @return {@link Boolean} true if query dent successfully null otherwise..*/
    Boolean updateServerWithFlag(Integer serverId, Boolean is_default);
}
