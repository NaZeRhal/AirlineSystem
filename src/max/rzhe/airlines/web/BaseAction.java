package max.rzhe.airlines.web;

import ch.qos.logback.classic.Logger;
import max.rzhe.airlines.entity.Entity;
import max.rzhe.airlines.entity.User;
import max.rzhe.airlines.service.BaseService;
import max.rzhe.airlines.service.exception.ServiceException;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

public abstract class BaseAction<T extends Entity, S extends BaseService<T, Long>> {
    private static Logger logger = (Logger) LoggerFactory.getLogger(BaseAction.class.getName());

    protected ActionResult getEntityListAction(HttpServletRequest request, S service, String attributeListName) throws ServletException {
        List<T> entities;
        try {
            entities = service.findAll();
            if (entities == null) {
                entities = Collections.emptyList();
            }
            request.setAttribute(attributeListName, entities);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
        return null;
    }

    protected ActionResult getEntityDeleteAction(HttpServletRequest request, S service, String idParameter, String goodActionDirection, String badActionDirection) throws ServletException {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        String idString = request.getParameter(idParameter);
        String serviceName = service.getClass().getSimpleName();
        String entityName = serviceName.substring(0, 1).toUpperCase() + serviceName.substring(1, serviceName.indexOf("Service"));
        Long id = null;
        if (idString != null && !idString.isEmpty()) {
            try {
                id = Long.parseLong(idString);
            } catch (NumberFormatException e) {
                throw new ServletException(e);
            }
        }
        if (null != id) {
            try {
                service.delete(id);
                logger.info("{} with id={} was deleted by '{}'", entityName, id, currentUser.getLogin());
            } catch (ServiceException e) {
                return new ActionResult(badActionDirection);
            }
        }
        return new ActionResult(goodActionDirection);
    }

    protected Long parseId(HttpServletRequest request, String idParameter) {
        String idString = request.getParameter(idParameter);
        Long id = null;
        if (idString != null && !idString.isEmpty()) {
            try {
                id = Long.parseLong(idString);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return id;
    }
}
