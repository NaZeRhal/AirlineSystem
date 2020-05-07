package max.rzhe.airlines.web.crew;

import max.rzhe.airlines.entity.CrewMan;
import max.rzhe.airlines.entity.CrewManVsFlight;
import max.rzhe.airlines.entity.Flight;
import max.rzhe.airlines.service.CrewManVsFlightService;
import max.rzhe.airlines.service.FlightService;
import max.rzhe.airlines.service.exception.ServiceException;
import max.rzhe.airlines.web.Action;
import max.rzhe.airlines.web.ActionResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class CrewManVsFlightListAction implements Action {
    private CrewManVsFlightService crewManVsFlightService;
    private FlightService flightService;

    public void setCrewManVsFlightService(CrewManVsFlightService crewManVsFlightService) {
        this.crewManVsFlightService = crewManVsFlightService;
    }

    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String idString = request.getParameter("flightId");
        Long flightId = null;
        if (idString != null && !idString.isEmpty()) {
            try {
                flightId = Long.parseLong(idString);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        List<CrewManVsFlight> crewManVsFlights;
        List<CrewMan> crewManList = new ArrayList<>();
        try {
            Flight flight = flightService.findById(flightId);
            if (flight != null) {
                crewManVsFlights = crewManVsFlightService.findCrewManOnBoard(flight);
                if (crewManVsFlights.size() != 0) {
                    for (CrewManVsFlight crewManVsFlight : crewManVsFlights) {
                        crewManList.add(crewManVsFlight.getCrewMan());
                    }
                }
            }
            request.setAttribute("flight", flight);
            request.setAttribute("crewManList", crewManList);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
        return null;
    }
}
