package max.rzhe.airlines.web.crewmen;

import max.rzhe.airlines.entity.CrewMan;
import max.rzhe.airlines.service.CrewManService;
import max.rzhe.airlines.web.Action;
import max.rzhe.airlines.web.ActionResult;
import max.rzhe.airlines.web.BaseAction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CrewManDeleteAction extends BaseAction<CrewMan, CrewManService> implements Action {
    private CrewManService crewManService;

    public void setCrewManService(CrewManService crewManService) {
        this.crewManService = crewManService;
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String goodActionDirection = "/crewmen/list.html?goodMessage=application.operation.success";
        String badActionDirection = "/crewmen/edit.html?badMessage=application.operation.error";
        return this.getEntityDeleteAction(request, crewManService, "crewManId", goodActionDirection, badActionDirection);
    }
}
