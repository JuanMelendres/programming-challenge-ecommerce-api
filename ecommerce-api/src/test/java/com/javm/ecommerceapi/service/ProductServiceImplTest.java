package com.javm.ecommerceapi.service;

import com.javm.ecommerceapi.entities.Product;
import com.javm.ecommerceapi.repository.ProductRepository;
import com.javm.ecommerceapi.service.product.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product mockProduct;

    @BeforeEach
    void setUp() {
        mockProduct = new Product();
        mockProduct.setId(1L);
        mockProduct.setName("Test Product");
        mockProduct.setPrice(100.0);
        mockProduct.setDescription("A test product description");
    }

    @Test
    void testGetProducts() {
        List<Product> mockProducts = List.of(mockProduct);
        when(productRepository.findAll()).thenReturn(mockProducts);

        List<Product> products = productService.getProducts();

        assertEquals(1, products.size());
        assertEquals(mockProduct, products.get(0));
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testGetProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));

        Optional<Product> product = productService.getProduct(1L);

        assertTrue(product.isPresent());
        assertEquals(mockProduct, product.get());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateProduct() {
        when(productRepository.save(mockProduct)).thenReturn(mockProduct);

        Product createdProduct = productService.createProduct(mockProduct);

        assertNotNull(createdProduct);
        assertEquals(mockProduct, createdProduct);
        verify(productRepository, times(1)).save(mockProduct);
    }

    @Test
    void testUpdateProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));
        when(productRepository.save(mockProduct)).thenReturn(mockProduct);

        Optional<Product> updatedProduct = productService.updateProduct(1L, mockProduct);

        assertTrue(updatedProduct.isPresent());
        assertEquals(mockProduct, updatedProduct.get());
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(mockProduct);
    }

    @Test
    void testDeleteProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));

        Optional<Product> deletedProduct = productService.deleteProduct(1L);

        assertTrue(deletedProduct.isEmpty());
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).delete(mockProduct);
    }
}
