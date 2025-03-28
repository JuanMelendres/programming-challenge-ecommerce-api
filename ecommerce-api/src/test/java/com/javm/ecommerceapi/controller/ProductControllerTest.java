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
import static org.springframework.http.HttpStatus.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductServiceImpl productService;

    @InjectMocks
    private ProductController productController;

    private Product mockProduct1;
    private Product mockProduct2;

    @BeforeEach
    void setUp() {
        mockProduct1 = new Product();
        mockProduct1.setId(1L);
        mockProduct1.setName("Product 1");
        mockProduct1.setDescription("Product 1");

        mockProduct2 = new Product();
        mockProduct2.setId(2L);
        mockProduct2.setName("Product 2");
        mockProduct2.setDescription("Product 2");
    }

    @Test
    void testGetProducts() {
        List<Product> mockProducts = List.of(mockProduct1, mockProduct2);
        when(productService.getProducts()).thenReturn(mockProducts);

        ResponseEntity<List<Product>> response = productController.getProducts();

        assertEquals(OK, response.getStatusCode());
        assertEquals(mockProducts, response.getBody());
        verify(productService, times(1)).getProducts();
    }

    @Test
    void testGetProduct() {
        when(productService.getProduct(1L)).thenReturn(Optional.of(mockProduct1));

        ResponseEntity<Product> response = productController.getProduct(1L);

        assertEquals(OK, response.getStatusCode());
        assertEquals(mockProduct1, response.getBody());
        verify(productService, times(1)).getProduct(1L);
    }

    @Test
    void testCreateProduct() {
        when(productService.createProduct(mockProduct1)).thenReturn(mockProduct1);

        ResponseEntity<Product> response = productController.createProduct(mockProduct1);

        assertEquals(CREATED, response.getStatusCode());
        assertEquals(mockProduct1, response.getBody());
        verify(productService, times(1)).createProduct(mockProduct1);
    }

    @Test
    void testUpdateProduct() {
        when(productService.updateProduct(1L, mockProduct1)).thenReturn(Optional.of(mockProduct1));

        ResponseEntity<Product> response = productController.updateProduct(1L, mockProduct1);

        assertEquals(OK, response.getStatusCode());
        assertEquals(mockProduct1, response.getBody());
        verify(productService, times(1)).updateProduct(1L, mockProduct1);
    }

    @Test
    void testDeleteProduct() {
        when(productService.getProduct(1L)).thenReturn(Optional.of(mockProduct1));
        when(productService.deleteProduct(1L)).thenReturn(Optional.of(mockProduct1));

        ResponseEntity<Void> response = productController.deleteProduct(1L);

        assertEquals(NO_CONTENT, response.getStatusCode());
        verify(productService, times(1)).deleteProduct(1L);
    }

}

