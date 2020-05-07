package max.rzhe.airlines.web.flights;

import max.rzhe.airlines.entity.Flight;
import max.rzhe.airlines.service.FlightService;
import max.rzhe.airlines.web.Action;
import max.rzhe.airlines.web.ActionResult;
import max.rzhe.airlines.web.BaseAction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FlightsDeleteAction extends BaseAction<Flight, FlightService> implements Action {
    private FlightService flightService;

    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String goodActionDirection = "/flights/list.html?goodMessage=application.operation.success";
        String badActionDirection = "/flights/list.html?badMessage=application.operation.error";
        return this.getEntityDeleteAction(request, flightService, "flightId", goodActionDirection, badActionDirection);
    }
}
