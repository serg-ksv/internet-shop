package com.ksv.internetshop.service.impl;

import com.ksv.internetshop.dao.ShoppingCartDao;
import com.ksv.internetshop.lib.Inject;
import com.ksv.internetshop.lib.Service;
import com.ksv.internetshop.model.Product;
import com.ksv.internetshop.model.ShoppingCart;
import com.ksv.internetshop.service.ShoppingCartService;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Override
    public ShoppingCart addProduct(ShoppingCart shoppingCart, Product product) {
        shoppingCart.getProducts().add(product);
        return shoppingCartDao.update(shoppingCart);
    }

    @Override
    public boolean deleteProduct(ShoppingCart shoppingCart, Product product) {
        boolean hasRemoved = shoppingCart.getProducts()
                .removeIf(p -> p.getId().equals(product.getId()));
        if (hasRemoved) {
            shoppingCartDao.update(shoppingCart);
        }
        return hasRemoved;
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.getProducts().clear();
        shoppingCartDao.update(shoppingCart);
    }

    @Override
    public ShoppingCart getByUserId(Long userId) {
        return shoppingCartDao.getAll().stream()
                .filter(shoppingCart -> shoppingCart.getUserId().equals(userId))
                .findFirst()
                .orElse(shoppingCartDao.create(new ShoppingCart(userId)));
    }

    @Override
    public List<Product> getAllProducts(ShoppingCart shoppingCart) {
        return shoppingCart.getProducts();
    }

    @Override
    public ShoppingCart get(Long id) {
        return shoppingCartDao.get(id).get();
    }

    @Override
    public List<ShoppingCart> getAll() {
        return shoppingCartDao.getAll();
    }

    @Override
    public boolean delete(Long id) {
        return shoppingCartDao.delete(id);
    }
}
