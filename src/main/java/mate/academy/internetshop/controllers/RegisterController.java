package mate.academy.internetshop.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

public class RegisterController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internetshop");
    private final UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        var name = req.getParameter("name");
        var email = req.getParameter("email");
        var login = req.getParameter("login");
        var password = req.getParameter("password");
        var confirmPassword = req.getParameter("confirmPassword");

        if (name.isEmpty() || email.isEmpty()
                || login.isEmpty() || password.isEmpty()) {
            req.setAttribute("message", "The fields should not be empty!");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
        } else if (!password.equals(confirmPassword)) {
            req.setAttribute("message", "Passwords do not match!");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
        } else {
            var user = new User(name, email, login, password);
            userService.create(user);
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }
}
