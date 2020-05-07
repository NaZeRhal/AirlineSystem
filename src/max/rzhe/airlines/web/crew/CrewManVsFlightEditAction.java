package max.rzhe.airlines.web.crew;

import max.rzhe.airlines.entity.CrewMan;
import max.rzhe.airlines.entity.CrewManVsFlight;
import max.rzhe.airlines.entity.Flight;
import max.rzhe.airlines.service.CrewManService;
import max.rzhe.airlines.service.CrewManVsFlightService;
import max.rzhe.airlines.service.FlightService;
import max.rzhe.airlines.service.exception.ServiceException;
import max.rzhe.airlines.web.Action;
import max.rzhe.airlines.web.ActionResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class CrewManVsFlightEditAction implements Action {
    private CrewManVsFlightService crewManVsFlightService;
    private FlightService flightService;
    private CrewManService crewManService;

    public void setCrewManVsFlightService(CrewManVsFlightService crewManVsFlightService) {
        this.crewManVsFlightService = crewManVsFlightService;
    }

    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
    }

    public void setCrewManService(CrewManService crewManService) {
        this.crewManService = crewManService;
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String flightIdString = request.getParameter("flightId");
        Long flightId = null;
        if (flightIdString != null && !flightIdString.isEmpty()) {
            try {
                flightId = Long.parseLong(flightIdString);
            } catch (NumberFormatException e) {
                throw new ServletException(e);
            }
        }

        try {
            List<CrewMan> freeCrewManList = new ArrayList<>();
            List<CrewMan> onBoardCrewManList = new ArrayList<>();
            Flight flight = flightService.findById(flightId);
            if (flight != null) {
                List<CrewManVsFlight> freeList = crewManVsFlightService.findFreeCrewMan(flight, 20);
                if (freeList.size() != 0) {
                    for (CrewManVsFlight crewManVsFlight : freeList) {
                        freeCrewManList.add(crewManVsFlight.getCrewMan());
                    }
                }
                List<CrewManVsFlight> onBoardList = crewManVsFlightService.findCrewManOnBoard(flight);
                if (onBoardList.size() != 0) {
                    for (CrewManVsFlight crewManVsFlight : onBoardList) {
                        onBoardCrewManList.add(crewManVsFlight.getCrewMan());
                    }
                }
            }
            request.setAttribute("freeCrewManList", freeCrewManList);
            request.setAttribute("onBoardCrewManList", onBoardCrewManList);
            request.setAttribute("flight", flight);


            String crewManIdString = request.getParameter("crewManId");
            Long crewManId = null;
            if (crewManIdString != null && !crewManIdString.isEmpty()) {
                try {
                    crewManId = Long.parseLong(crewManIdString);
                } catch (NumberFormatException e) {
                    throw new ServletException(e);
                }
            }

            if (crewManId != null) {
                CrewMan crewMan = crewManService.findById(crewManId);
                Map<String, Integer> profMap = countProfessions(onBoardCrewManList);
                if (crewMan.getProfession().getName().equals("PILOT") && profMap.get("PILOT") > 1) {
                    return new ActionResult("/crew/edit.html?flightId=" + flightId + "&badMessage=crew.table.pilot.enough");
                } else if (crewMan.getProfession().getName().equals("NAVIGATOR") && profMap.get("NAVIGATOR") > 0) {
                    return new ActionResult("/crew/edit.html?flightId=" + flightId + "&badMessage=crew.table.navigators.enough");
                } else if (crewMan.getProfession().getName().equals("RADIOMAN") && profMap.get("RADIOMAN") > 0) {
                    return new ActionResult("/crew/edit.html?flightId=" + flightId + "&badMessage=crew.table.radioman.enough");
                } else if (crewMan.getProfession().getName().equals("STEWARD") && profMap.get("STEWARD") > 3) {
                    return new ActionResult("/crew/edit.html?flightId=" + flightId + "&badMessage=crew.table.steward.enough");
                } else if (onBoardCrewManList.size() > 5) {
                    return new ActionResult("/crew/edit.html?flightId=" + flightId + "&badMessage=crew.table.crewOnBoard.enough");
                } else {
                    CrewManVsFlight crewManVsFlight = new CrewManVsFlight();
                    crewManVsFlight.setFlight(flight);
                    crewManVsFlight.setCrewMan(crewMan);
                    crewManVsFlightService.add(crewManVsFlight);
                    return new ActionResult("/crew/edit.html?flightId=" + flightId + "&goodMessage=application.operation.success");
                }
            }

        } catch (ServiceException e) {
            throw new ServletException(e);
        }
        return null;
    }

    private Map<String, Integer> countProfessions(List<CrewMan> list) {
        Map<String, Integer> professionsMap = new HashMap<>();
        int pilots = 0;
        int navigators = 0;
        int radiomen = 0;
        int stewards = 0;
        if (list.size() > 0) {
            for (CrewMan crewMan : list) {
                if (crewMan.getProfession().getName().equals("PILOT")) {
                    pilots++;
                }
                if (crewMan.getProfession().getName().equals("NAVIGATOR")) {
                    navigators++;
                }
                if (crewMan.getProfession().getName().equals("RADIOMAN")) {
                    radiomen++;
                }
                if (crewMan.getProfession().getName().equals("STEWARD")) {
                    stewards++;
                }
            }
        }
        professionsMap.put("PILOT", pilots);
        professionsMap.put("NAVIGATOR", navigators);
        professionsMap.put("RADIOMAN", radiomen);
        professionsMap.put("STEWARD", stewards);
        return professionsMap;
    }
}
