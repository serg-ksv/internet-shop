package mate.academy.internetshop.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;

public class GetAllOrdersController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("mate.academy");
    private final OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);
    private final UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        var userId = (Long) req.getSession().getAttribute("user_id");
        List<Order> allOrders = orderService.getUserOrders(userService.get(userId));
        req.setAttribute("orders", allOrders);
        req.getRequestDispatcher("/WEB-INF/views/orders/all.jsp").forward(req, resp);
    }
}
