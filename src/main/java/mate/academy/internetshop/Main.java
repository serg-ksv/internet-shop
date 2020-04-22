package mate.academy.internetshop;

import java.math.BigDecimal;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.ProductService;

public class Main {
    private static Injector injector = Injector.getInstance("mate.academy.internetshop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);

        Product product1 = new Product("product1", new BigDecimal(100));
        Product product2 = new Product("product2", new BigDecimal(200));
        Product product3 = new Product("product3", new BigDecimal(300));
        productService.create(product1);
        productService.create(product2);
        productService.create(product3);

        for (Product product : productService.getAll()) {
            System.out.println(product.toString());
        }

        Product getProduct = productService.get(1L);
        System.out.println(getProduct);

        productService.update(productService.get(2L));
        productService.delete(3L);

        for (Product product : productService.getAll()) {
            System.out.println(product.toString());
        }
    }
}
