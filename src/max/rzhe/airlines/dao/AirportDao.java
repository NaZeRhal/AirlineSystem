package max.rzhe.airlines.dao;

import max.rzhe.airlines.entity.Airport;

import java.io.Serializable;
import java.util.List;

public interface AirportDao extends Dao<Airport, Long> {
    Airport findByAirportCode(String airportCode) throws DaoException;
}
