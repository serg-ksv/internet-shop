package com.ksv.internetshop.dao.jdbc;

import com.ksv.internetshop.dao.ProductDao;
import com.ksv.internetshop.exception.DataProcessingException;
import com.ksv.internetshop.lib.Dao;
import com.ksv.internetshop.model.Product;
import com.ksv.internetshop.util.ConnectionUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class ProductDaoJdbcImpl implements ProductDao {
    @Override
    public Product create(Product product) {
        var query = "INSERT INTO products (name, price) VALUES (?, ?)";
        try (var connection = ConnectionUtil.getConnection()) {
            var preparedStatement = connection
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setBigDecimal(2, product.getPrice());
            preparedStatement.executeUpdate();
            var resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                var productId = resultSet.getLong(1);
                product.setId(productId);
            }
            return product;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create product. " + e.getMessage());
        }
    }

    @Override
    public Optional<Product> get(Long id) {
        var query = "SELECT * FROM products WHERE product_id = ?";
        try (var connection = ConnectionUtil.getConnection()) {
            var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();
            Product product = null;
            while (resultSet.next()) {
                product = getProductFromResultSet(resultSet);
            }
            return Optional.of(product);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't retrieve product. " + e.getMessage());
        }
    }

    @Override
    public List<Product> getAll() {
        var query = "SELECT * FROM products";
        try (var connection = ConnectionUtil.getConnection()) {
            var preparedStatement = connection.prepareStatement(query);
            var resultSet = preparedStatement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                var product = getProductFromResultSet(resultSet);
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't retrieve products. " + e.getMessage());
        }
    }

    @Override
    public Product update(Product product) {
        var query = "UPDATE products SET name = ?, price = ? WHERE product_id = ?";
        try (var connection = ConnectionUtil.getConnection()) {
            var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setBigDecimal(2, product.getPrice());
            preparedStatement.setLong(3, product.getId());
            preparedStatement.executeUpdate();
            return product;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update product. " + e.getMessage());
        }
    }

    @Override
    public boolean delete(Long id) {
        var query = "DELETE FROM products WHERE product_id = ?";
        try (var connection = ConnectionUtil.getConnection()) {
            var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete product. " + e.getMessage());
        }
    }

    public Product getProductFromResultSet(ResultSet resultSet) throws SQLException {
        var productId = resultSet.getLong("product_id");
        var productName = resultSet.getString("name");
        var productPrice = resultSet.getBigDecimal("price");
        var product = new Product(productName, productPrice);
        product.setId(productId);
        return product;
    }
}
