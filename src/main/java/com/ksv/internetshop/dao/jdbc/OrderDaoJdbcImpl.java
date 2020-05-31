package com.ksv.internetshop.dao.jdbc;

import com.ksv.internetshop.dao.OrderDao;
import com.ksv.internetshop.exception.DataProcessingException;
import com.ksv.internetshop.lib.Dao;
import com.ksv.internetshop.model.Order;
import com.ksv.internetshop.model.Product;
import com.ksv.internetshop.model.User;
import com.ksv.internetshop.util.ConnectionUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class OrderDaoJdbcImpl implements OrderDao {
    @Override
    public List<Order> getUserOrders(User user) {
        var query = "SELECT * FROM orders WHERE user_id = ?";
        try (var connection = ConnectionUtil.getConnection()) {
            var prepareStatement = connection.prepareStatement(query);
            prepareStatement.setLong(1, user.getId());
            var resultSet = prepareStatement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                var order = (getOrderFromResultSet(resultSet));
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't retrieve user's orders. " + e.getMessage());
        }
    }

    @Override
    public Order create(Order order) {
        var query = "INSERT INTO orders (user_id) VALUES (?)";
        try (var connection = ConnectionUtil.getConnection()) {
            var preparedStatement = connection
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, order.getUserId());
            preparedStatement.executeUpdate();
            var resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                var orderId = resultSet.getLong(1);
                order.setId(orderId);
            }
            addProductsToOrder(order);
            return order;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create order. " + e.getMessage());
        }
    }

    @Override
    public Optional<Order> get(Long orderId) {
        var query = "SELECT * FROM orders WHERE order_id = ?";
        try (var connection = ConnectionUtil.getConnection()) {
            var prepareStatement = connection.prepareStatement(query);
            prepareStatement.setLong(1, orderId);
            var resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                var order = getOrderFromResultSet(resultSet);
                return Optional.of(order);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't retrieve order. " + e.getMessage());
        }
    }

    @Override
    public List<Order> getAll() {
        var query = "SELECT * FROM orders";
        try (var connection = ConnectionUtil.getConnection()) {
            var preparedStatement = connection.prepareStatement(query);
            var resultSet = preparedStatement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                var order = getOrderFromResultSet(resultSet);
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't retrieve orders. " + e.getMessage());
        }
    }

    @Override
    public Order update(Order order) {
        var query = "UPDATE orders SET user_id = ? WHERE order_id = ?";
        try (var connection = ConnectionUtil.getConnection()) {
            var prepareStatement = connection.prepareStatement(query);
            prepareStatement.setLong(1, order.getUserId());
            prepareStatement.setLong(2, order.getId());
            prepareStatement.executeUpdate();
            deleteOrderFromOrdersProducts(order.getId());
            addProductsToOrder(order);
            return order;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update order. " + e.getMessage());
        }
    }

    @Override
    public boolean delete(Long orderId) {
        var query = "DELETE FROM orders WHERE order_id = ?";
        deleteOrderFromOrdersProducts(orderId);
        try (var connection = ConnectionUtil.getConnection()) {
            var prepareStatement = connection.prepareStatement(query);
            prepareStatement.setLong(1, orderId);
            prepareStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete order. " + e.getMessage());
        }
    }

    private Order getOrderFromResultSet(ResultSet resultSet) throws SQLException {
        var orderId = resultSet.getLong("order_id");
        var userId = resultSet.getLong("user_id");
        return new Order(orderId, userId, getProductsFromOrder(orderId));
    }

    private void addProductsToOrder(Order order) {
        var query = "INSERT INTO orders_products (order_id, product_id) VALUES (?, ?)";
        try (var connection = ConnectionUtil.getConnection()) {
            var prepareStatement = connection.prepareStatement(query);
            for (var product : order.getProducts()) {
                prepareStatement.setLong(1, order.getId());
                prepareStatement.setLong(2, product.getId());
                prepareStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't add products to order. " + e.getMessage());
        }
    }

    private List<Product> getProductsFromOrder(Long orderId) {
        var query = "SELECT product_id, name, price FROM orders_products "
                + "JOIN products USING (product_id) WHERE order_id = ?";
        try (var connection = ConnectionUtil.getConnection()) {
            var prepareStatement = connection.prepareStatement(query);
            prepareStatement.setLong(1, orderId);
            var resultSet = prepareStatement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                var productId = resultSet.getLong("product_id");
                var productName = resultSet.getString("name");
                var productPrice = resultSet.getBigDecimal("price");
                var product = new Product(productName, productPrice);
                product.setId(productId);
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't retrieve products. " + e.getMessage());
        }
    }

    private void deleteOrderFromOrdersProducts(Long orderId) {
        var query = "DELETE FROM orders_products WHERE order_id = ?";
        try (var connection = ConnectionUtil.getConnection()) {
            var prepareStatement = connection.prepareStatement(query);
            prepareStatement.setLong(1, orderId);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete order from orders_products. "
                    + e.getMessage());
        }
    }
}
