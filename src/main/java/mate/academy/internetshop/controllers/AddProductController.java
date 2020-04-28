package mate.academy.internetshop.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.ProductService;

public class AddProductController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internetshop");
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
        if (name.isEmpty() || price.isEmpty()) {
            req.setAttribute("message", "The fields should not be empty!");
            req.getRequestDispatcher("/WEB-INF/views/products/addProduct.jsp").forward(req, resp);
        } else {
            var product = new Product(name, BigDecimal.valueOf(Long.parseLong(price)));
            var addedProduct = productService.create(product);
            req.setAttribute("product", addedProduct);
            req.getRequestDispatcher("/WEB-INF/views/products/productInfo.jsp").forward(req, resp);
        }
    }
}
