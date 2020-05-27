package com.ksv.internetshop.controller;

import com.ksv.internetshop.lib.Injector;
import com.ksv.internetshop.service.OrderService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteOrderController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("com.ksv");
    private final OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        var orderId = req.getParameter("id");
        orderService.delete(Long.valueOf(orderId));
        resp.sendRedirect(req.getContextPath() + "/orders/all");
    }
}
