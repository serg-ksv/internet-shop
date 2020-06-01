package com.ksv.internetshop.service.impl;

import com.ksv.internetshop.dao.OrderDao;
import com.ksv.internetshop.lib.Inject;
import com.ksv.internetshop.lib.Service;
import com.ksv.internetshop.model.Order;
import com.ksv.internetshop.model.Product;
import com.ksv.internetshop.model.User;
import com.ksv.internetshop.service.OrderService;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Inject
    private OrderDao orderDao;

    @Override
    public Order completeOrder(List<Product> products, Long userId) {
        Order order = new Order(userId, products);
        orderDao.create(order);
        return order;
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return orderDao.getUserOrders(user);
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id).get();
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public boolean delete(Long id) {
        return orderDao.delete(id);
    }
}
