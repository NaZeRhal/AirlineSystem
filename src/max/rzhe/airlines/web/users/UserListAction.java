package max.rzhe.airlines.web.users;

import max.rzhe.airlines.entity.User;
import max.rzhe.airlines.service.UserService;
import max.rzhe.airlines.web.Action;
import max.rzhe.airlines.web.ActionResult;
import max.rzhe.airlines.web.BaseAction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserListAction extends BaseAction<User, UserService> implements Action {
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        return this.getEntityListAction(request, userService, "users");
    }
}
