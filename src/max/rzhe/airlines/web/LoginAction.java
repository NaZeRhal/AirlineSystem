package max.rzhe.airlines.web;

import max.rzhe.airlines.entity.User;
import max.rzhe.airlines.service.UserService;
import max.rzhe.airlines.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginAction implements Action {
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (null != login && null != password) {
            try {
                User user = userService.findByLogin(login);
                if (null != user) {
                    if (user.getPassword().equals(password)) {
                        HttpSession session = request.getSession();
                        session.setAttribute("currentUser", user);
                        return new ActionResult("/index.html");
                    } else {
                        return new ActionResult("/login.html?badMessage=login.message.incorrect.password");
                    }
                } else {
                    return new ActionResult("/login.html?badMessage=login.message.unknown.login");
                }
            } catch (ServiceException e) {
                throw new ServletException(e);
            }
        } else {
            return null;
        }
    }
}
