package com.javm.ecommerceapi.controller;

import com.javm.ecommerceapi.entities.Order;
import com.javm.ecommerceapi.service.order.OrderServiceImpl;
import com.javm.ecommerceapi.util.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    private OrderServiceImpl orderService;

    @InjectMocks
    private OrderController orderController;

    private Order mockOrder;

    @BeforeEach
    void setUp() {
        mockOrder = new Order();
        mockOrder.setId(1L);
        mockOrder.setOrderDate(LocalDate.now());
        mockOrder.setStatus(OrderStatus.PENDING);
    }

    @Test
    void testGetOrders() {
        List<Order> mockOrders = List.of(mockOrder);
        when(orderService.getOrders()).thenReturn(mockOrders);

        ResponseEntity<List<Order>> response = orderController.getOrders();

        assertEquals(OK, response.getStatusCode());
        assertEquals(mockOrders, response.getBody());
        verify(orderService, times(1)).getOrders();
    }

    @Test
    void testGetOrder() {
        when(orderService.getOrder(1L)).thenReturn(Optional.of(mockOrder));

        ResponseEntity<Order> response = orderController.getOrder(1L);

        assertEquals(OK, response.getStatusCode());
        assertEquals(mockOrder, response.getBody());
        verify(orderService, times(1)).getOrder(1L);
    }

    @Test
    void testCreateOrder() {
        when(orderService.createOrder(mockOrder)).thenReturn(mockOrder);

        ResponseEntity<Order> response = orderController.createOrder(mockOrder);

        assertEquals(CREATED, response.getStatusCode());
        assertEquals(mockOrder, response.getBody());
        verify(orderService, times(1)).createOrder(mockOrder);
    }

    @Test
    void testUpdateOrder() {
        when(orderService.updateOrder(1L, mockOrder)).thenReturn(Optional.of(mockOrder));

        ResponseEntity<Order> response = orderController.updateOrder(1L, mockOrder);

        assertEquals(OK, response.getStatusCode());
        assertEquals(mockOrder, response.getBody());
        verify(orderService, times(1)).updateOrder(1L, mockOrder);
    }

    @Test
    void testDeleteOrder() {
        when(orderService.deleteOrder(1L)).thenReturn(Optional.of(mockOrder));

        ResponseEntity<Order> response = orderController.deleteOrder(1L);

        assertEquals(NO_CONTENT, response.getStatusCode());
        verify(orderService, times(1)).deleteOrder(1L);
    }
}


