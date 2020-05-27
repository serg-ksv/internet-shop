package com.ksv.internetshop.controller;

import com.ksv.internetshop.lib.Injector;
import com.ksv.internetshop.model.Order;
import com.ksv.internetshop.service.OrderService;
import com.ksv.internetshop.service.UserService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetUserOrdersController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("com.ksv");
    private final OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);
    private final UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        var userId = (Long) req.getSession().getAttribute("user_id");
        List<Order> userOrders = orderService.getUserOrders(userService.get(userId));
        req.setAttribute("orders", userOrders);
        req.getRequestDispatcher("/WEB-INF/views/orders/all.jsp").forward(req, resp);
    }
}
