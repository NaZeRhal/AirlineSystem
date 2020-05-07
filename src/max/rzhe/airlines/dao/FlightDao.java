package max.rzhe.airlines.dao;

import max.rzhe.airlines.entity.Airport;
import max.rzhe.airlines.entity.Flight;
import max.rzhe.airlines.entity.FlightStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightDao extends Dao<Flight, Long> {
    List<Flight> findByFlightStatus(FlightStatus flightStatus) throws DaoException;
}
