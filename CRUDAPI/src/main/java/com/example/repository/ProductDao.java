package com.example.repository;

import com.example.model.Product;

import java.util.List;

public interface ProductDao {
    boolean saveProduct(Product product);

    List<Product> fetchAllProduct();

    Product fetchProductById(long id);

    boolean deleteProduct(long id);

    boolean updateProduct(long id, Product product);
}
