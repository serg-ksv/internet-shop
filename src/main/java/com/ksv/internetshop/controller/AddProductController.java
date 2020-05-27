package com.ksv.internetshop.controller;

import com.ksv.internetshop.lib.Injector;
import com.ksv.internetshop.model.Product;
import com.ksv.internetshop.service.ProductService;
import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddProductController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("com.ksv.internetshop");
    private final ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/products/addProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        var name = req.getParameter("name");
        var price = req.getParameter("price");
        var product = new Product(name, BigDecimal.valueOf(Long.parseLong(price)));
        var addedProduct = productService.create(product);
        req.setAttribute("product", addedProduct);
        req.getRequestDispatcher("/WEB-INF/views/products/productInfo.jsp").forward(req, resp);
    }
}
