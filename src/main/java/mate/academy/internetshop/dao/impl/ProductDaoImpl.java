package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import mate.academy.internetshop.dao.ProductDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Product;

@Dao
public class ProductDaoImpl implements ProductDao {
    @Override
    public Product create(Product product) {
        Storage.addProduct(product);
        return product;
    }

    @Override
    public Optional<Product> get(Long id) {
        return Storage.products.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Product> getAll() {
        return Storage.products;
    }

    @Override
    public Product update(Product product) {
        IntStream.range(0, Storage.products.size())
                .filter(i -> Storage.products.get(i).getId().equals(product.getId()))
                .forEach(i -> Storage.products.set(i, product));
        return product;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.products.removeIf(product -> product.getId().equals(id));
    }
}
