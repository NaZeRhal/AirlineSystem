package max.rzhe.airlines.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Action {
    ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException;
}
