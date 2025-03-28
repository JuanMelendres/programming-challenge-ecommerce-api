package com.javm.ecommerceapi.controller;

import com.javm.ecommerceapi.entities.Product;
import com.javm.ecommerceapi.service.product.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductServiceImpl productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        // Initialize mocks if necessary
    }

    @Test
    void testGetProducts() {
        List<Product> mockProducts = List.of(new Product(), new Product());
        when(productService.getProducts()).thenReturn(mockProducts);

        ResponseEntity<List<Product>> response = productController.getProducts();

        assertEquals(OK, response.getStatusCode());
        assertEquals(mockProducts, response.getBody());
        verify(productService, times(1)).getProducts();
    }

    @Test
    void testGetProduct() {
        Product mockProduct = new Product();
        when(productService.getProduct(1L)).thenReturn(Optional.of(mockProduct));

        ResponseEntity<Product> response = productController.getProduct(1L);

        assertEquals(OK, response.getStatusCode());
        assertEquals(mockProduct, response.getBody());
        verify(productService, times(1)).getProduct(1L);
    }

    @Test
    void testCreateProduct() {
        Product mockProduct = new Product();
        when(productService.createProduct(mockProduct)).thenReturn(mockProduct);

        ResponseEntity<Product> response = productController.createProduct(mockProduct);

        assertEquals(CREATED, response.getStatusCode());
        assertEquals(mockProduct, response.getBody());
        verify(productService, times(1)).createProduct(mockProduct);
    }

    @Test
    void testUpdateProduct() {
        Product mockProduct = new Product();
        when(productService.updateProduct(1L, mockProduct)).thenReturn(Optional.of(mockProduct));

        ResponseEntity<Product> response = productController.updateProduct(1L, mockProduct);

        assertEquals(OK, response.getStatusCode());
        assertEquals(mockProduct, response.getBody());
        verify(productService, times(1)).updateProduct(1L, mockProduct);
    }

    @Test
    void testDeleteProduct() {
        Product mockProduct = new Product();
        when(productService.deleteProduct(1L)).thenReturn(Optional.of(mockProduct));

        ResponseEntity<Product> response = productController.deleteProduct(1L);

        assertEquals(OK, response.getStatusCode());
        assertEquals(mockProduct, response.getBody());
        verify(productService, times(1)).deleteProduct(1L);
    }
}

