package max.rzhe.airlines.web.crewmen;

import ch.qos.logback.classic.Logger;
import max.rzhe.airlines.entity.CrewMan;
import max.rzhe.airlines.entity.Profession;
import max.rzhe.airlines.entity.User;
import max.rzhe.airlines.service.CrewManService;
import max.rzhe.airlines.service.ProfessionService;
import max.rzhe.airlines.service.exception.ServiceException;
import max.rzhe.airlines.web.Action;
import max.rzhe.airlines.web.ActionResult;
import max.rzhe.airlines.web.BaseAction;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;

public class CrewManEditAction extends BaseAction<CrewMan, CrewManService> implements Action {
    private static Logger logger = (Logger) LoggerFactory.getLogger(CrewManEditAction.class);
    private CrewManService crewManService;
    private ProfessionService professionService;

    public void setCrewManService(CrewManService crewManService) {
        this.crewManService = crewManService;
    }

    public void setProfessionService(ProfessionService professionService) {
        this.professionService = professionService;
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        Long crewManId = parseId(request, "crewManId");

        if ("GET".equals(request.getMethod())) {
            List<Profession> professions;
            CrewMan crewMan;
            try {
                professions = professionService.findAll();
                if (professions == null) {
                    professions = Collections.emptyList();
                }
                request.setAttribute("professions", professions);
                if (crewManId != null) {
                    crewMan = crewManService.findById(crewManId);
                    request.setAttribute("crewman", crewMan);
                }
            } catch (ServiceException e) {
                throw new ServletException(e);
            }
            return null;
        }

        if ("POST".equals(request.getMethod())) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String dateOfBirth = request.getParameter("dateOfBirth");
            String professionIdString = request.getParameter("professionId");
            Long professionId;
            if (firstName != null && lastName != null && dateOfBirth != null && professionIdString != null) {
                try {
                    professionId = Long.parseLong(professionIdString);
                } catch (NumberFormatException e) {
                    throw new ServletException(e);
                }
                LocalDate date;
                try {
                    date = LocalDate.parse(dateOfBirth);
                    if (date.isAfter(LocalDate.now())) {
                        if (crewManId != null) {
                            return new ActionResult(String.format(request.getServletPath() +
                                    "?crewManId=%d&badMessage=crewman.edit.date.incorrect", crewManId));
                        } else {
                            return new ActionResult(request.getServletPath() +
                                    "?badMessage=crewman.edit.date.incorrect");
                        }
                    }
                } catch (DateTimeParseException e) {
                    return new ActionResult(String.format(request.getServletPath() +
                            "?crewManId=%d&badMessage=crewman.edit.date.parse.error", crewManId));
                }
                try {
                    Profession profession;
                    profession = professionService.findById(professionId);
                    CrewMan crewMan = new CrewMan();
                    crewMan.setId(crewManId);
                    crewMan.setFirstName(firstName);
                    crewMan.setLastName(lastName);
                    crewMan.setDateOfBirth(date);
                    crewMan.setProfession(profession);
                    if (crewManId == null) {
                        crewManService.add(crewMan);
                        logger.info("New {} was added by '{}'", crewMan, currentUser.getLogin());
                        return new ActionResult(request.getServletPath() +
                                "?goodMessage=application.operation.success");
                    } else {
                        crewManService.edit(crewMan);
                        logger.info("{} was edited by '{}'", crewMan, currentUser.getLogin());
                        return new ActionResult(String.format(request.getServletPath() +
                                "?crewManId=%d&goodMessage=application.operation.success", crewMan.getId()));
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
