package dao;

import model.LocationDTO;
import model.ServerDTO;

import java.util.List;

/**
 * Created by Martha on 8/23/2016.
 */
public interface ServerDAO {

    /**
     * A SQL query string for creating server. Monitor.name
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
     * A SQL query string for reading all existing servers from server table.*/
    String READ_SERVERS = ""
            + "SELECT *"
            + " FROM server";

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
    String READ_LOCATION_ID_BY_SERVER_ID = ""
            + "SELECT location_id, location_name, addr"
            + " FROM location"
            + " WHERE location_id LIKE"
            + " (SELECT location_id "
            + " FROM server "
            + " WHERE server_id = ?)";

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
     * Reads server from server table by specified id.
     * @param serverId id of server to be retrieved.
     * @return {@link ServerDTO} only server_id, name, location_id and flag fields are initialized with
     * retrieved values.*/
    ServerDTO readServerById(Integer serverId);

    /**
     * Reads location id from specified server.
     * @param serverId id of server to be retrieved.
     * @return {@link LocationDTO} Location where specified server is situated*/
    LocationDTO readLocationIdByServerById(Integer serverId);

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
