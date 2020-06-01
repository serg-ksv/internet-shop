package com.ksv.internetshop.controller;

import com.ksv.internetshop.lib.Injector;
import com.ksv.internetshop.service.ProductService;
import com.ksv.internetshop.service.ShoppingCartService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProductFromShoppingCartController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("com.ksv");
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);
    private final ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        var userId = (Long) req.getSession().getAttribute("user_id");
        var shoppingCart = shoppingCartService.getByUserId(userId);
        var productId = req.getParameter("id");
        var product = productService.get(Long.valueOf(productId));
        shoppingCartService.deleteProduct(shoppingCart, product);
        resp.sendRedirect(req.getContextPath() + "/shopping-cart");
    }
}
