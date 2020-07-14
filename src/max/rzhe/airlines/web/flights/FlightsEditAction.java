package max.rzhe.airlines.web.flights;

import ch.qos.logback.classic.Logger;
import max.rzhe.airlines.entity.Airport;
import max.rzhe.airlines.entity.Flight;
import max.rzhe.airlines.entity.FlightStatus;
import max.rzhe.airlines.entity.User;
import max.rzhe.airlines.service.AirportService;
import max.rzhe.airlines.service.FlightService;
import max.rzhe.airlines.service.exception.ServiceException;
import max.rzhe.airlines.web.Action;
import max.rzhe.airlines.web.ActionResult;
import max.rzhe.airlines.web.BaseAction;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FlightsEditAction extends BaseAction<Flight, FlightService> implements Action {
    private static Logger logger = (Logger) LoggerFactory.getLogger(FlightsEditAction.class);
    private FlightService flightService;
    private AirportService airportService;

    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
    }

    public void setAirportService(AirportService airportService) {
        this.airportService = airportService;
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        Long id = parseId(request, "flightId");

        if ("GET".equals(request.getMethod())) {
            List<Airport> airports;
            Flight flight;
            try {
                airports = airportService.findAll();
                if (airports == null) {
                    airports = Collections.emptyList();
                }
                request.setAttribute("airports", airports);
                if (id != null) {
                    flight = flightService.findById(id);
                    request.setAttribute("flight", flight);
                }
                List<FlightStatus> flightStatuses = Arrays.asList(FlightStatus.values());
                request.setAttribute("flightStatuses", flightStatuses);
            } catch (ServiceException e) {
                throw new ServletException(e);
            }
            return null;
        }

        if ("POST".equals(request.getMethod())) {
            String departure = request.getParameter("departure");
            String arrival = request.getParameter("arrival");
            String status = request.getParameter("flightStatus");

            if (departure.equals(arrival)) {
                return new ActionResult(String.format("/flights/edit.html?flightId=%d" +
                        "&badMessage=flights.add.operation.airport.error", id));
            }
            LocalDateTime departureTime;
            LocalDateTime arrivalTime;
            try {
                departureTime = LocalDateTime.parse(request.getParameter("departureTime"));
                arrivalTime = LocalDateTime.parse(request.getParameter("arrivalTime"));
                if (departureTime.isBefore(LocalDateTime.now())) {
                    return new ActionResult(String.format("/flights/edit.html?flightId=%d" +
                            "&badMessage=flights.add.datetime.departureAirport.incorrect", id));
                }
                if (arrivalTime.isBefore(departureTime)) {
                    return new ActionResult(String.format("/flights/edit.html?flightId=%d" +
                            "&badMessage=flights.add.datetime.arrivalAirport.incorrect", id));
                }
            } catch (DateTimeParseException e) {
                return new ActionResult(String.format("/flights/edit.html?flightId=%d" +
                        "&badMessage=flights.add.datetime.parse.error", id));
            }

            String flightCode = departure.substring(0, 2) + arrival.substring(0, 2)
                    + String.valueOf(departureTime.hashCode()).substring(1, 5);
            try {
                Flight flight = new Flight();
                flight.setId(id);
                flight.setFlightCode(flightCode);
                flight.setDepartureAirport(airportService.findByAirportCode(departure));
                flight.setArrivalAirport(airportService.findByAirportCode(arrival));
                flight.setDepartureTime(departureTime);
                flight.setArrivalTime(arrivalTime);
                flight.setFlightStatus(FlightStatus.valueOf(status));
                if (id == null) {
                    flightService.add(flight);
                    logger.info("New {} was added by '{}'", flight, currentUser.getLogin());
                    return new ActionResult(request.getServletPath() +
                            "?goodMessage=application.operation.success");
                } else {
                    flightService.edit(flight);
                    logger.info("{} was edited by '{}'", flight, currentUser.getLogin());
                    return new ActionResult(String.format(request.getServletPath() +
                            "?flightId=%d&goodMessage=application.operation.success", flight.getId()));
                }
            } catch (ServiceException e) {
                throw new ServletException(e);
            }
        } else {
            return null;
        }
    }
}
