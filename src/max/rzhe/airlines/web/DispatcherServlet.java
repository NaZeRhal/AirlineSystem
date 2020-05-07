package max.rzhe.airlines.web;

import max.rzhe.airlines.ioc.IoCContainer;
import max.rzhe.airlines.ioc.IoCException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        String context = req.getContextPath();
        url = url.substring(context.length());
        int position = url.lastIndexOf(".html");
        if(position != -1) {
            url = url.substring(0, position);
        }
        ActionResult actionResult = null;
        try(IoCContainer ioc = new IoCContainer()) {
            Action action = ioc.get(Action.class, url);
            if(action != null) {
                actionResult = action.execute(req, resp);
            }
        } catch(IoCException e) {
            throw new ServletException(e);
        }
        if(actionResult != null && actionResult.isRedirect()) {
            resp.sendRedirect(context + actionResult.getUrl());
        } else {
            if(actionResult != null) {
                url = actionResult.getUrl();
            }
            req.getRequestDispatcher("/WEB-INF/jsp" + url + ".jsp").forward(req, resp);
        }
    }
}
