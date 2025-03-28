package com.javm.ecommerceapi.service;

import com.javm.ecommerceapi.entities.Order;
import com.javm.ecommerceapi.repository.OrderRepository;
import com.javm.ecommerceapi.service.order.OrderServiceImpl;
import com.javm.ecommerceapi.util.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

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
        when(orderRepository.findAll()).thenReturn(mockOrders);

        List<Order> orders = orderService.getOrders();

        assertEquals(1, orders.size());
        assertEquals(mockOrder, orders.get(0));
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void testGetOrder() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(mockOrder));

        Optional<Order> order = orderService.getOrder(1L);

        assertTrue(order.isPresent());
        assertEquals(mockOrder, order.get());
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateOrder() {
        when(orderRepository.save(mockOrder)).thenReturn(mockOrder);

        Order createdOrder = orderService.createOrder(mockOrder);

        assertNotNull(createdOrder);
        assertEquals(mockOrder, createdOrder);
        verify(orderRepository, times(1)).save(mockOrder);
    }

    @Test
    void testUpdateOrder() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(mockOrder));
        when(orderRepository.save(mockOrder)).thenReturn(mockOrder);

        Optional<Order> updatedOrder = orderService.updateOrder(1L, mockOrder);

        assertTrue(updatedOrder.isPresent());
        assertEquals(mockOrder, updatedOrder.get());
        verify(orderRepository, times(1)).findById(1L);
        verify(orderRepository, times(1)).save(mockOrder);
    }

    @Test
    void testDeleteOrder() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(mockOrder));

        Optional<Order> deletedOrder = orderService.deleteOrder(1L);

        assertTrue(deletedOrder.isEmpty());
        verify(orderRepository, times(1)).findById(1L);
        verify(orderRepository, times(1)).delete(mockOrder);
    }
}
