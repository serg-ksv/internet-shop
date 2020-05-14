package mate.academy.internetshop.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.ShoppingCartDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.util.ConnectionUtil;

@Dao
public class ShoppingCartDaoJdbcImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        var query = "INSERT INTO shopping_carts (user_id) VALUES (?)";
        try (var connection = ConnectionUtil.getConnection()) {
            var preparedStatement = connection
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, shoppingCart.getUserId());
            preparedStatement.executeUpdate();
            var resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                var cartId = resultSet.getLong(1);
                shoppingCart.setId(cartId);
            }
            return shoppingCart;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create cart. " + e.getMessage());
        }
    }

    @Override
    public Optional<ShoppingCart> get(Long id) {
        var query = "SELECT * FROM shopping_carts WHERE cart_id = ?";
        try (var connection = ConnectionUtil.getConnection()) {
            var prepareStatement = connection.prepareStatement(query);
            prepareStatement.setLong(1, id);
            var resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                var shoppingCart = getCartFromResultSet(resultSet);
                return Optional.of(shoppingCart);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't retrieve cart. " + e.getMessage());
        }
    }

    @Override
    public List<ShoppingCart> getAll() {
        var query = "SELECT * FROM shopping_carts";
        try (var connection = ConnectionUtil.getConnection()) {
            var preparedStatement = connection.prepareStatement(query);
            var resultSet = preparedStatement.executeQuery();
            List<ShoppingCart> shoppingCarts = new ArrayList<>();
            while (resultSet.next()) {
                var shoppingCart = getCartFromResultSet(resultSet);
                shoppingCarts.add(shoppingCart);
            }
            return shoppingCarts;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't retrieve carts. " + e.getMessage());
        }
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        var query = "UPDATE shopping_carts SET user_id = ? WHERE cart_id = ?";
        try (var connection = ConnectionUtil.getConnection()) {
            var prepareStatement = connection.prepareStatement(query);
            prepareStatement.setLong(1, shoppingCart.getUserId());
            prepareStatement.setLong(2, shoppingCart.getId());
            prepareStatement.executeUpdate();
            deleteCartFromCartsProducts(shoppingCart.getId());
            addProductsToCart(shoppingCart);
            return shoppingCart;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update cart. " + e.getMessage());
        }
    }

    @Override
    public boolean delete(Long id) {
        var query = "DELETE FROM shopping_carts WHERE cart_id = ?";
        try (var connection = ConnectionUtil.getConnection()) {
            deleteCartFromCartsProducts(id);
            var prepareStatement = connection.prepareStatement(query);
            prepareStatement.setLong(1, id);
            prepareStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete cart. " + e.getMessage());
        }
    }

    private ShoppingCart getCartFromResultSet(ResultSet resultSet) throws SQLException {
        var cartId = resultSet.getLong("cart_id");
        var userId = resultSet.getLong("user_id");
        var shoppingCart = new ShoppingCart(userId);
        shoppingCart.setId(cartId);
        shoppingCart.setProducts(getProductsFromCart(cartId));
        return shoppingCart;
    }

    private void addProductsToCart(ShoppingCart shoppingCart) {
        var query = "INSERT INTO shopping_carts_products (cart_id, product_id) VALUES (?, ?)";
        try (var connection = ConnectionUtil.getConnection()) {
            for (var product : shoppingCart.getProducts()) {
                var prepareStatement = connection.prepareStatement(query);
                prepareStatement.setLong(1, shoppingCart.getId());
                prepareStatement.setLong(2, product.getId());
                prepareStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't add products to cart. " + e.getMessage());
        }
    }

    private List<Product> getProductsFromCart(Long cartId) {
        var query = "SELECT product_id, name, price FROM shopping_carts_products "
                + "JOIN products USING (product_id) WHERE cart_id = ?";
        try (var connection = ConnectionUtil.getConnection()) {
            var prepareStatement = connection.prepareStatement(query);
            prepareStatement.setLong(1, cartId);
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

    private void deleteCartFromCartsProducts(Long cartId) {
        var query = "DELETE FROM shopping_carts_products WHERE cart_id = ?";
        try (var connection = ConnectionUtil.getConnection()) {
            var prepareStatement = connection.prepareStatement(query);
            prepareStatement.setLong(1, cartId);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete cart from shopping_carts_products. "
                    + e.getMessage());
        }
    }
}
