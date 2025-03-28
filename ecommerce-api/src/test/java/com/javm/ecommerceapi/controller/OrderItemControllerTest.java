package com.javm.ecommerceapi.controller;

import com.javm.ecommerceapi.entities.Order;
import com.javm.ecommerceapi.entities.OrderItem;
import com.javm.ecommerceapi.entities.Product;
import com.javm.ecommerceapi.service.orderItem.OrderItemServiceImpl;
import com.javm.ecommerceapi.util.OrderStatus;
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
class OrderItemControllerTest {

    @Mock
    private OrderItemServiceImpl orderItemService;

    @InjectMocks
    private OrderItemController orderItemController;

    private OrderItem mockOrderItem;

    @BeforeEach
    void setUp() {
        Product mockProduct1 = new Product();
        mockProduct1.setName("Product 1");
        mockProduct1.setPrice(25.0);

        Order mockOrder1 = new Order();
        mockOrder1.setId(1L);
        mockOrder1.setStatus(OrderStatus.PENDING);

        mockOrderItem = new OrderItem();
        mockOrderItem.setId(1L);
        mockOrderItem.setQuantity(3);
        mockOrderItem.setProduct(mockProduct1);
        mockOrderItem.setOrder(mockOrder1);
    }

    @Test
    void testGetOrderItems() {
        List<OrderItem> mockOrderItems = List.of(mockOrderItem);
        when(orderItemService.getOrderItems()).thenReturn(mockOrderItems);

        ResponseEntity<List<OrderItem>> response = orderItemController.getOrderItems();

        assertEquals(OK, response.getStatusCode());
        assertEquals(mockOrderItems, response.getBody());
        verify(orderItemService, times(1)).getOrderItems();
    }

    @Test
    void testGetOrderItem() {
        when(orderItemService.getOrderItem(1L)).thenReturn(Optional.of(mockOrderItem));

        ResponseEntity<OrderItem> response = orderItemController.getOrderItem(1L);

        assertEquals(OK, response.getStatusCode());
        assertEquals(mockOrderItem, response.getBody());
        verify(orderItemService, times(1)).getOrderItem(1L);
    }

    @Test
    void testCreateOrderItem() {
        when(orderItemService.createOrderItem(mockOrderItem)).thenReturn(mockOrderItem);

        ResponseEntity<OrderItem> response = orderItemController.createOrderItem(mockOrderItem);

        assertEquals(CREATED, response.getStatusCode());
        assertEquals(mockOrderItem, response.getBody());
        verify(orderItemService, times(1)).createOrderItem(mockOrderItem);
    }

    @Test
    void testUpdateOrderItem() {
        when(orderItemService.updateOrderItem(1L, mockOrderItem)).thenReturn(Optional.of(mockOrderItem));

        ResponseEntity<OrderItem> response = orderItemController.updateOrderItem(1L, mockOrderItem);

        assertEquals(OK, response.getStatusCode());
        assertEquals(mockOrderItem, response.getBody());
        verify(orderItemService, times(1)).updateOrderItem(1L, mockOrderItem);
    }

    @Test
    void testDeleteOrderItem() {
        when(orderItemService.getOrderItem(1L)).thenReturn(Optional.of(mockOrderItem));
        when(orderItemService.deleteOrderItem(1L)).thenReturn(Optional.of(mockOrderItem));

        ResponseEntity<Void> response = orderItemController.deleteOrderItem(1L);

        assertEquals(NO_CONTENT, response.getStatusCode());
        verify(orderItemService, times(1)).deleteOrderItem(1L);
    }
}
