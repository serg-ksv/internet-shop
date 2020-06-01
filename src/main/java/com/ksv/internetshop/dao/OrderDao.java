package com.ksv.internetshop.dao;

import com.ksv.internetshop.model.Order;
import com.ksv.internetshop.model.User;
import java.util.List;

public interface OrderDao extends GenericDao<Order, Long> {
    List<Order> getUserOrders(User user);
}
