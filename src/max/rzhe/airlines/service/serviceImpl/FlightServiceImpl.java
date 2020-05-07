package max.rzhe.airlines.service.serviceImpl;

import max.rzhe.airlines.dao.DaoException;
import max.rzhe.airlines.dao.FlightDao;
import max.rzhe.airlines.entity.Flight;
import max.rzhe.airlines.entity.FlightStatus;
import max.rzhe.airlines.service.FlightService;
import max.rzhe.airlines.service.exception.ServiceException;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public class FlightServiceImpl extends AbstractBaseService<Flight, Long, FlightDao> implements FlightService {
    @Override
    public void setRepository(FlightDao repository) {
        super.setRepository(repository);
    }

    @Override
    public Flight findById(Long id) throws ServiceException {
        return updateFlightStatus(super.findById(id));
    }

    @Override
    public List<Flight> findAll() throws ServiceException {
        List<Flight> flights = super.findAll();
        for (Flight flight : flights) {
            updateFlightStatus(flight);
        }
        return flights;
    }

    @Override
    public List<Flight> findByFlightStatus(FlightStatus flightStatus) throws ServiceException {
        List<Flight> flights = new ArrayList<>();
        getTransactionHandler().runWithTransaction(() -> {
            try {
                List<Flight> flightList = getRepository().findByFlightStatus(flightStatus);
                flights.addAll(flightList);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        });
        if (flights.size() == 0) {
            return Collections.emptyList();
        }
        return flights;
    }

    private Flight updateFlightStatus(Flight flight) throws ServiceException {
        try {
            ZonedDateTime dur1 = ZonedDateTime.now(ZoneId.systemDefault());
            ZonedDateTime dur2 = ZonedDateTime.of(flight.getDepartureTime(), ZoneId.systemDefault());
            long min = Duration.between(dur1, dur2).toMinutes();
            long flightTimeMin = Duration.between(flight.getArrivalTime(), flight.getDepartureTime()).toMinutes();
            if (flight.getFlightStatus() == FlightStatus.UNREGISTERED
                    || flight.getFlightStatus() == FlightStatus.CANCELLED
                    || flight.getFlightStatus() == FlightStatus.DONE) {
                return flight;
            } else if (min > 180) {
                flight.setFlightStatus(FlightStatus.OPENED);
                getRepository().update(flight);
            } else if (min < 180 && min > 40) {
                flight.setFlightStatus(FlightStatus.CHECKIN);
                getRepository().update(flight);
            } else if (min <= 40 && min > 0) {
                flight.setFlightStatus(FlightStatus.BOARDING);
                getRepository().update(flight);
            } else if ((flightTimeMin - min) < 0) {
                flight.setFlightStatus(FlightStatus.DEPARTED);
                getRepository().update(flight);
            } else if ((flightTimeMin - min) >= 0 && (flightTimeMin - min) < 60) {
                flight.setFlightStatus(FlightStatus.LANDED);
                getRepository().update(flight);
            } else if ((flightTimeMin - min) >= 60) {
                flight.setFlightStatus(FlightStatus.DONE);
                getRepository().update(flight);
            }
            return flight;
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }
}
