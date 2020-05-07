package max.rzhe.airlines.service;

import max.rzhe.airlines.entity.Flight;
import max.rzhe.airlines.entity.FlightStatus;
import max.rzhe.airlines.service.exception.ServiceException;

import java.util.List;

public interface FlightService extends BaseService<Flight, Long> {

    List<Flight> findByFlightStatus(FlightStatus flightStatus) throws ServiceException;

}
