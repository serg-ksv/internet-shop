package com.ksv.internetshop.service;

import com.ksv.internetshop.model.Order;
import com.ksv.internetshop.model.Product;
import com.ksv.internetshop.model.User;
import java.util.List;

public interface OrderService extends GenericService<Order, Long> {
    Order completeOrder(List<Product> products, Long userId);

    List<Order> getUserOrders(User user);
}
