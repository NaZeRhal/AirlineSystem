package max.rzhe.airlines.web.crew;

import max.rzhe.airlines.service.CrewManVsFlightService;
import max.rzhe.airlines.service.exception.ServiceException;
import max.rzhe.airlines.web.Action;
import max.rzhe.airlines.web.ActionResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CrewManVsFlightDeleteAction implements Action {
    private CrewManVsFlightService crewManVsFlightService;

    public void setCrewManVsFlightService(CrewManVsFlightService crewManVsFlightService) {
        this.crewManVsFlightService = crewManVsFlightService;
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String flightIdString = request.getParameter("flightId");
        String crewManIdString = request.getParameter("crewManId");
        Long flightId = null;
        Long crewManId = null;
        if (flightIdString != null && !flightIdString.isEmpty() && crewManIdString != null
                && !crewManIdString.isEmpty()) {
            try {
                flightId = Long.parseLong(flightIdString);
                crewManId = Long.parseLong(crewManIdString);
            } catch (NumberFormatException e) {
                throw new ServletException(e);
            }
        }
        try {
            crewManVsFlightService.deleteByFlightAndCrewMan(flightId, crewManId);
        } catch (ServiceException e) {
            return new ActionResult("/crew/edit.html?flightId=" + flightId + "&badMessage=application.operation.error");
        }
        return new ActionResult("/crew/edit.html?flightId=" + flightId + "&goodMessage=application.operation.success");
    }
}
