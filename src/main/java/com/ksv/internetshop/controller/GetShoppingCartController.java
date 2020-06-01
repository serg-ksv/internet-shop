package com.ksv.internetshop.controller;

import com.ksv.internetshop.lib.Injector;
import com.ksv.internetshop.service.ShoppingCartService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetShoppingCartController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("com.ksv");
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        var userId = (Long) req.getSession().getAttribute("user_id");
        var shoppingCart = shoppingCartService.getByUserId(userId);
        req.setAttribute("products", shoppingCart.getProducts());
        req.getRequestDispatcher("/WEB-INF/views/shoppingCart.jsp").forward(req, resp);
    }
}
