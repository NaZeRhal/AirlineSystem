package max.rzhe.airlines.web;

import ch.qos.logback.classic.Logger;
import max.rzhe.airlines.entity.User;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class LogoutAction implements Action {
    private static Logger logger = (Logger) LoggerFactory.getLogger(LogoutAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        User currentUser = (User) req.getSession().getAttribute("currentUser");
        HttpSession session = req.getSession(false);
        if(session != null) {
            session.invalidate();
            logger.info("User '{}' logged out of session", currentUser.getLogin());
        }
        return new ActionResult("/index.html");
    }
}
