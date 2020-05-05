package mate.academy.internetshop.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.ProductService;
import mate.academy.internetshop.service.UserService;

public class InjectDataController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internetshop");
    private final UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);
    private final ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        var bob = new User("Bob", "bob@mail.com", "bob", "bobPass");
        bob.setRoles(Set.of(Role.of("USER")));
        userService.create(bob);
        var alice = new User("Alice", "alice@mail.com", "alice", "alicePass");
        alice.setRoles(Set.of(Role.of("USER")));
        userService.create(alice);
        var admin = new User("Admin", "admin@mail.com", "admin", "1234");
        admin.setRoles(Set.of(Role.of("ADMIN")));
        userService.create(admin);

        var product1 = new Product("product1", new BigDecimal(100));
        var product2 = new Product("product2", new BigDecimal(200));
        productService.create(product1);
        productService.create(product2);

        req.getRequestDispatcher("/WEB-INF/views/injectData.jsp").forward(req, resp);
    }
}
