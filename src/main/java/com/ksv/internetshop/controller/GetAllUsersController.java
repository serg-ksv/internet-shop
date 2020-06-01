package com.ksv.internetshop.controller;

import com.ksv.internetshop.lib.Injector;
import com.ksv.internetshop.model.User;
import com.ksv.internetshop.service.UserService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllUsersController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("com.ksv.internetshop");
    private final UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<User> allUsers = userService.getAll();
        req.setAttribute("users", allUsers);
        req.getRequestDispatcher("/WEB-INF/views/users/all.jsp").forward(req, resp);
    }
}
