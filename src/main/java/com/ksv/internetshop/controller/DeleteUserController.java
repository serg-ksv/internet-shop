package com.ksv.internetshop.controller;

import com.ksv.internetshop.lib.Injector;
import com.ksv.internetshop.service.UserService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("com.ksv.internetshop");
    private final UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        var userId = req.getParameter("id");
        userService.delete(Long.valueOf(userId));
        resp.sendRedirect(req.getContextPath() + "/users/all");
    }
}
