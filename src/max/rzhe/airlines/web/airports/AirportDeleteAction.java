package max.rzhe.airlines.web.airports;

import max.rzhe.airlines.entity.Airport;
import max.rzhe.airlines.service.AirportService;
import max.rzhe.airlines.web.Action;
import max.rzhe.airlines.web.ActionResult;
import max.rzhe.airlines.web.BaseAction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AirportDeleteAction extends BaseAction<Airport, AirportService> implements Action {
    private AirportService airportService;

    public void setAirportService(AirportService airportService) {
        this.airportService = airportService;
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String goodActionDirection = "/airports/list.html?goodMessage=application.operation.success";
        String badActionDirection = "/airports/list.html?badMessage=application.operation.error";
        return this.getEntityDeleteAction(request, airportService, "airportId", goodActionDirection, badActionDirection);
    }
}
