package com.ksv.internetshop.controller;

import com.ksv.internetshop.lib.Injector;
import com.ksv.internetshop.service.ProductService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProductController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("com.ksv.internetshop");
    private final ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        var productId = req.getParameter("id");
        productService.delete(Long.valueOf(productId));
        resp.sendRedirect(req.getContextPath() + "/admin/products/all");
    }
}
