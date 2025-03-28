package com.javm.ecommerceapi.service.product;

import com.javm.ecommerceapi.entities.Product;
import com.javm.ecommerceapi.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        log.info("Get all products");
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> getProduct(Long id) {
        log.info("Get a product by ID {}", id);
        return this.productRepository.findById(id);
    }

    @Override
    public Product createProduct(Product product) {
        log.info("Create a new product: {}", product);
        return this.productRepository.save(product);
    }

    @Override
    public Optional<Product> updateProduct(Long id, Product product) {
        Optional<Product> productExist = this.productRepository.findById(id);

        if (productExist.isPresent()) {

            log.info("Updating product with id {}", product.getId());
            productExist.get().setName(product.getName());
            productExist.get().setPrice(product.getPrice());
            productExist.get().setDescription(product.getDescription());

            this.productRepository.save(productExist.get());

            return productExist;
        }

        return Optional.empty();
    }

    @Override
    public Optional<Product> deleteProduct(Long id) {
        Optional<Product> productExist = this.productRepository.findById(id);

        if (productExist.isPresent()) {
            log.info("Deleting product: {}", productExist.get());
            this.productRepository.delete(productExist.get());
        }

        return Optional.empty();
    }
}
