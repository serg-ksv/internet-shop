package com.ksv.internetshop.controller;

import com.ksv.internetshop.lib.Injector;
import com.ksv.internetshop.service.ProductService;
import com.ksv.internetshop.service.ShoppingCartService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddProductToShoppingCartController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("com.ksv");
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);
    private final ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        var userId = (Long) req.getSession().getAttribute("user_id");
        var productId = req.getParameter("id");
        var shoppingCart = shoppingCartService.getByUserId(userId);
        shoppingCartService.addProduct(shoppingCart, productService.get(Long.valueOf(productId)));
        resp.sendRedirect(req.getContextPath() + "/shopping-cart");
    }
}
