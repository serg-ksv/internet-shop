package com.ksv.internetshop.controller;

import com.ksv.internetshop.lib.Injector;
import com.ksv.internetshop.model.Product;
import com.ksv.internetshop.model.Role;
import com.ksv.internetshop.model.User;
import com.ksv.internetshop.service.ProductService;
import com.ksv.internetshop.service.UserService;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InjectDataController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("com.ksv.internetshop");
    private final UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);
    private final ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        var admin = new User("Admin", "admin@mail.com", "admin", "admin");
        admin.setRoles(Set.of(Role.of("ADMIN")));
        userService.create(admin);
        var bob = new User("Bob", "bob@mail.com", "bob", "1234");
        bob.setRoles(Set.of(Role.of("USER")));
        userService.create(bob);

        var product1 = new Product("product1", new BigDecimal(100));
        var product2 = new Product("product2", new BigDecimal(200));
        productService.create(product1);
        productService.create(product2);

        req.getRequestDispatcher("/WEB-INF/views/injectData.jsp").forward(req, resp);
    }
}
