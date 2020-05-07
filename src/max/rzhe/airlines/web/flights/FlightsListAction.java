package max.rzhe.airlines.web.flights;

import max.rzhe.airlines.entity.Flight;
import max.rzhe.airlines.entity.FlightStatus;
import max.rzhe.airlines.entity.User;
import max.rzhe.airlines.service.FlightService;
import max.rzhe.airlines.service.exception.ServiceException;
import max.rzhe.airlines.web.Action;
import max.rzhe.airlines.web.ActionResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class FlightsListAction implements Action {
    private FlightService flightService;

    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        List<Flight> flights = new ArrayList<>();
        int actionNumber = checkAction(request);
        try {
            if (actionNumber == 0) {
                User user = (User) (request.getSession(false).getAttribute("currentUser"));
                if ("ADMINISTRATOR".equals(user.getUserType().getName())) {
                    actionNumber = 1;
                } else {
                    actionNumber = 4;
                }
            }
            if (actionNumber == 1) {
                flights.addAll(flightService.findByFlightStatus(FlightStatus.BOARDING));
                flights.addAll(flightService.findByFlightStatus(FlightStatus.CHECKIN));
                flights.addAll(flightService.findByFlightStatus(FlightStatus.DEPARTED));
                flights.addAll(flightService.findByFlightStatus(FlightStatus.LANDED));
                flights.addAll(flightService.findByFlightStatus(FlightStatus.OPENED));
            }
            if (actionNumber == 2) {
                flights = flightService.findByFlightStatus(FlightStatus.DONE);
            }
            if (actionNumber == 3) {
                flights = flightService.findByFlightStatus(FlightStatus.CANCELLED);
            }
            if (actionNumber == 4) {
                flights = flightService.findByFlightStatus(FlightStatus.UNREGISTERED);
            }
            if (actionNumber == 5) {
                flights = flightService.findAll();
            }
        } catch (ServiceException e) {
            throw new ServletException();
        }
        request.setAttribute("flights", flights);
        request.setAttribute("actionNumber", actionNumber);
        return null;
    }

    private int checkAction(HttpServletRequest req) {
        if (req.getParameter("actualList") != null) {
            return 1;
        }
        if (req.getParameter("doneList") != null) {
            return 2;
        }
        if (req.getParameter("cancelledList") != null) {
            return 3;
        }
        if (req.getParameter("unregisteredList") != null) {
            return 4;
        }
        if (req.getParameter("fullList") != null) {
            return 5;
        }
        return 0;
    }
}
