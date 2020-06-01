package com.ksv.internetshop.service;

import com.ksv.internetshop.model.Product;

public interface ProductService extends GenericService<Product, Long> {
    Product create(Product product);

    Product update(Product product);
}
