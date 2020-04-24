package mate.academy.internetshop.dao;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;

public interface OrderDao {

    Order create(Order order);

    List<Order> getUserOrders(User user);

    Optional<Order> get(Long id);

    List<Order> getAll();

    Order update(Order order);

    boolean delete(Long id);
}
