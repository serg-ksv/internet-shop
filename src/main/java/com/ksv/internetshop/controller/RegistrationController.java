package com.ksv.internetshop.controller;

import com.ksv.internetshop.lib.Injector;
import com.ksv.internetshop.model.Role;
import com.ksv.internetshop.model.User;
import com.ksv.internetshop.service.UserService;
import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("com.ksv.internetshop");
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

        if (!password.equals(confirmPassword)) {
            req.setAttribute("message", "Passwords do not match!");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
        } else {
            var user = new User(name, email, login, password);
            user.setRoles(Set.of(Role.of("USER")));
            userService.create(user);
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }
}
