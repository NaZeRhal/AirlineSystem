package max.rzhe.airlines.web.users;

import max.rzhe.airlines.entity.User;
import max.rzhe.airlines.entity.UserType;
import max.rzhe.airlines.service.UserService;
import max.rzhe.airlines.service.UserTypeService;
import max.rzhe.airlines.service.exception.ServiceException;
import max.rzhe.airlines.web.Action;
import max.rzhe.airlines.web.ActionResult;
import max.rzhe.airlines.web.BaseAction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class UserEditAction extends BaseAction<User, UserService> implements Action {
    private UserService userService;
    private UserTypeService userTypeService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setUserTypeService(UserTypeService userTypeService) {
        this.userTypeService = userTypeService;
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Long id = parseId(request, "userId");

        if ("GET".equals(request.getMethod())) {
            User user;
            List<UserType> userTypes;
            try {
                userTypes = userTypeService.findAll();
                if (userTypes == null) {
                    userTypes = Collections.emptyList();
                }
                request.setAttribute("userTypes", userTypes);
                if (null != id) {
                    user = userService.findById(id);
                    request.setAttribute("user", user);
                }
            } catch (ServiceException e) {
                throw new ServletException(e);
            }
            return null;
        }

        if ("POST".equals(request.getMethod())) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            String userTypeIdString = request.getParameter("userTypeId");
            Long userTypeId;
            if (null != firstName && null != lastName && null != login
                    && null != password && null != userTypeIdString) {
                try {
                    userTypeId = Long.parseLong(userTypeIdString);
                } catch (NumberFormatException e) {
                    throw new ServletException(e);
                }
                try {
                    UserType userType = userTypeService.findById(userTypeId);
                    if (userType != null) {
                        User user = new User();
                        user.setId(id);
                        user.setFirstName(firstName);
                        user.setLastName(lastName);
                        user.setLogin(login);
                        user.setPassword(password);
                        user.setUserType(userType);
                        if (id == null) {
                            userService.add(user);
                            return new ActionResult(request.getServletPath() +
                                    "?goodMessage=application.operation.success");
                        } else {
                            userService.edit(user);
                            request.setAttribute("user", user);
                            return new ActionResult(String.format(request.getServletPath() +
                                    "?userId=%d&goodMessage=application.operation.success", user.getId()));
                        }
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
