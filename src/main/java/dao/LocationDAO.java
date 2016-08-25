package dao;

import model.LocationDTO;

/**
 * Created by Martha on 8/23/2016.
 */
public interface LocationDAO {

    /**
     * A SQL query string for creating location. Location.name and location.addr
     * parameters needed to be injected under "1" and "2" indexes in query statement
     * using statement.setString(parameter index, values) method.*/
    String CREATE_LOCATION = ""
            + " INSERT INTO location (name, addr),\n"
            + " VALUES (?, ?),\n"
            + ")";

    /**
     * A SQL query string for reading location_id; name and addr parametrs from location table.
     * Location.name and location.add. Location.id needed to be injected under "1"
     * index in query statement using statement.setString(parameter index, values) method.*/
    String READ_LOCATION = ""
            + " SELECT location_id, name, addr,\n"
            + " FROM location,\n"
            + " WHERE location_id = ?,\n"
            + ")";

    /**
     * Creates new location in location table.
     * @param locationDTO new location to be created, only name and addr fields are used,
     *                    id is autoincrement.
     * @return {@link LocationDTO} created location object.*/
    LocationDTO createLocation(LocationDTO locationDTO);

    /**
     * Reads location from location table by specified id.
     * @param locationId id of location to be retrieved.
     * @return {@link LocationDTO} only location_id, name and addr fields are initialized with
     * retrieved values.*/
    LocationDTO readLocationById(Integer locationId);

}
