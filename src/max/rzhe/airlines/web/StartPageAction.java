package max.rzhe.airlines.web;

import max.rzhe.airlines.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StartPageAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("currentUser");
            if (user != null) {
                switch (user.getUserType().getName()) {
                    case "MODERATOR":
                        return new ActionResult("/users/list.html");
                    case "ADMINISTRATOR":
                        return new ActionResult("/flights/list.html");
                    case "DISPATCHER":
                        return new ActionResult("/flights/list.html");
                    default:
                        return new ActionResult("/login.html");
                }
            } else {
                return new ActionResult("/login.html");
            }
        } else {
            return new ActionResult("/login.html");
        }
    }
}
