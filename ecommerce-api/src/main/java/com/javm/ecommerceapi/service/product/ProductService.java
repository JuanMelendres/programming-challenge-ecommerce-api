package com.javm.ecommerceapi.service.product;

import com.javm.ecommerceapi.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> getProducts();
    public Optional<Product> getProduct(Long id);
    public Product createProduct(Product product);
    public Optional<Product> updateProduct(Long id, Product product);
    public Optional<Product> deleteProduct(Long id);
}
