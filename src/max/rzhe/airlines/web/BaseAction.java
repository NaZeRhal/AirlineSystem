package max.rzhe.airlines.web;

import max.rzhe.airlines.entity.Entity;
import max.rzhe.airlines.service.BaseService;
import max.rzhe.airlines.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

public abstract class BaseAction<T extends Entity, S extends BaseService<T, Long>> {

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
        String idString = request.getParameter(idParameter);
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
