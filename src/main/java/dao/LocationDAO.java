package dao;

import entity.LocationDTO;

import java.util.List;

/**
 * Created by Martha on 8/23/2016.
 */
public interface LocationDAO {

    /**
     * A SQL query string for creating location. Location.name and location.addr
     * parameters needed to be injected under "1" and "2" indexes in query statement
     * using statement.setString(parameter index, values) method.*/
    String CREATE_LOCATION = ""
            + "INSERT INTO location (location_name, addr)"
            + " VALUES (?, ?)";
    /**
     * A SQL query string for reading location_id; name and addr parametrs from location table.
     * Location.id needed to be injected under "1"
     * index in query statement using statement.setString(parameter index, values) method.*/
    String READ_LOCATION_BY_ID = ""
            + "SELECT location_id, location_name, addr"
            + " FROM location"
            + " WHERE location_id = ?";

    /**
     * A SQL query string for reading location_id; name and addr parametrs from location table.
     * Location.name needed to be injected under "1"
     * index in query statement using statement.setString(parameter index, values) method.*/
    String READ_LOCATION_BY_NAMW = ""
            + "SELECT location_id, location_name, addr"
            + " FROM location"
            + " WHERE location_name = ?";

    /**
     * A SQL query string for reading all existing locations from location table.*/
    String READ_LOCATIONS = ""
            + "SELECT *"
            + " FROM location";

    /**
     * Creates new location in location table.
     * @param locationDTO new location to be created, only name and addr fields are used,
     *                    id is autoincrement.
     * @return {@link Integer} Has value if object was created and null otherwise.*/
    Integer createLocation(LocationDTO locationDTO);

    /**
     * Reads all existing locations from location table.
     * @return {@link List<LocationDTO>} only location_id, name and addr fields are initialized with
     * retrieved values.*/
    List<LocationDTO> readLocations();

    /**
     * Reads location from location table by specified id.
     * @param locationId id of location to be retrieved.
     * @return {@link LocationDTO} only location_id, name and addr fields are initialized with
     * retrieved values.*/
    LocationDTO readLocationById(Integer locationId);

    /**
     * Reads location from location table by specified name.
     * @param locationName name of location to be retrieved.
     * @return {@link LocationDTO} only location_id, name and addr fields are initialized with
     * retrieved values.*/
    LocationDTO readLocationByName(String locationName);
}
