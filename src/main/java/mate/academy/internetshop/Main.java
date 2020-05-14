package mate.academy.internetshop;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.ProductService;
import mate.academy.internetshop.service.ShoppingCartService;
import mate.academy.internetshop.service.UserService;

public class Main {
    private static Injector injector = Injector.getInstance("mate.academy.internetshop");

    public static void main(String[] args) {
        final ProductService productService =
                (ProductService) injector.getInstance(ProductService.class);
        final UserService userService =
                (UserService) injector.getInstance(UserService.class);
        final OrderService orderService =
                (OrderService) injector.getInstance(OrderService.class);
        final ShoppingCartService shoppingCartService =
                (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

        Product product1 = new Product("product1", new BigDecimal(100));
        Product product2 = new Product("product2", new BigDecimal(200));
        Product product3 = new Product("product3", new BigDecimal(300));
        productService.create(product1);
        productService.create(product2);
        productService.create(product3);
        productService.getAll()
                .forEach(product -> System.out.println(product.toString()));
        Product getProduct = productService.get(1L);
        System.out.println(getProduct);
        productService.update(productService.get(2L));
        productService.delete(3L);
        productService.getAll()
                .forEach(product -> System.out.println(product.toString()));

        User user1 = new User("user1", "user1@mail.com", "login1", "somePass1");
        User user2 = new User("user2", "user2@mail.com", "login2", "somePass2");
        User user3 = new User("user3", "user3@mail.com", "login3", "somePass3");
        userService.create(user1);
        userService.create(user2);
        userService.create(user3);
        userService.getAll()
                .forEach(user -> System.out.println(user.toString()));
        User getUser = userService.get(3L);
        System.out.println("Get user3:\n" + getUser);
        System.out.println("Update user1:\n" + userService.update(userService.get(1L)));
        System.out.println("Delete user2:" + userService.delete(2L));
        userService.getAll()
                .forEach(user -> System.out.println(user.toString()));

        final ShoppingCart user1Cart = shoppingCartService.getByUserId(user1.getId());
        shoppingCartService.addProduct(user1Cart, product1);
        shoppingCartService.addProduct(user1Cart, product2);
        List<Product> user1Products =
                shoppingCartService.getAllProducts(user1Cart);
        System.out.println("User1's products:");
        user1Products.forEach(product -> System.out.println(product.toString()));
        shoppingCartService.deleteProduct(user1Cart, product1);
        System.out.println("User1's products:\n" + user1Cart.getProducts());

        Order user1Order = orderService.completeOrder(
                shoppingCartService.getAllProducts(user1Cart), user1.getId());
        System.out.println(orderService.getAll());
        System.out.println(orderService.get(user1Order.getId()));
        System.out.println(orderService.getUserOrders(user1));
    }
}
