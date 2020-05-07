package max.rzhe.airlines.web.airports;

import max.rzhe.airlines.entity.Airport;
import max.rzhe.airlines.service.AirportService;
import max.rzhe.airlines.service.exception.ServiceException;
import max.rzhe.airlines.web.Action;
import max.rzhe.airlines.web.ActionResult;
import max.rzhe.airlines.web.BaseAction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AirportEditAction extends BaseAction<Airport, AirportService> implements Action {
    private AirportService airportService;

    public void setAirportService(AirportService airportService) {
        this.airportService = airportService;
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Long id = parseId(request, "airportId");

        if ("GET".equals(request.getMethod())) {
            Airport airport;
            if (id != null) {
                try {
                    airport = airportService.findById(id);
                    request.setAttribute("airport", airport);
                } catch (ServiceException e) {
                    throw new ServletException(e);
                }
            }
            return null;
        }
        if ("POST".equals(request.getMethod())) {
            String city = request.getParameter("city");
            String airportCode = request.getParameter("airportCode");
            if (city != null && airportCode != null) {
                try {
                    Airport airport = new Airport();
                    airport.setId(id);
                    airport.setCity(city);
                    airport.setAirportCode(airportCode);
                    if (id == null) {
                        airportService.add(airport);
                        return new ActionResult(request.getServletPath() +
                                "?goodMessage=application.operation.success");
                    } else {
                        airportService.edit(airport);
                        request.setAttribute("airport", airport);
                        return new ActionResult(String.format(request.getServletPath() +
                                "?airportId=%d&goodMessage=application.operation.success", airport.getId()));
                    }
                } catch (ServiceException e) {
                    throw new ServletException(e);
                }
            } else {
                return null;
            }
        }
            return null;
    }
}
