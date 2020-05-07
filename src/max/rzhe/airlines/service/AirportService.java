package max.rzhe.airlines.service;


import max.rzhe.airlines.entity.Airport;
import max.rzhe.airlines.service.exception.ServiceException;

import java.util.List;

public interface AirportService extends BaseService<Airport, Long> {
    Airport findByAirportCode(String airportCode) throws ServiceException;
}
