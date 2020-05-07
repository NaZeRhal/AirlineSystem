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

public class UserDeleteAction extends BaseAction<User, UserService> implements Action {
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String goodActionDirection = "/users/list.html?goodMessage=application.operation.success";
        String badActionDirection = "/users/edit.html?badMessage=application.operation.error";
        return this.getEntityDeleteAction(request, userService, "userId", goodActionDirection, badActionDirection);
    }
}
