package com.ksv.internetshop.controller;

import com.ksv.internetshop.lib.Injector;
import com.ksv.internetshop.service.OrderService;
import com.ksv.internetshop.service.ShoppingCartService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CompleteOrderController extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR = Injector.getInstance("com.ksv");
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);
    private final OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        var userId = (Long) req.getSession().getAttribute(USER_ID);
        var shoppingCart = shoppingCartService.getByUserId(userId);
        var products = List.copyOf(shoppingCart.getProducts());
        if (products.isEmpty()) {
            req.setAttribute("message", "Your cart is empty!");
            req.getRequestDispatcher("/WEB-INF/views/shoppingCart.jsp").forward(req, resp);
        } else {
            orderService.completeOrder(products, shoppingCart.getUserId());
            shoppingCartService.clear(shoppingCart);
            resp.sendRedirect(req.getContextPath() + "/orders/all");
        }
    }
}
