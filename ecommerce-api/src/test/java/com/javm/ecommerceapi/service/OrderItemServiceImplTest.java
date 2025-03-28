package com.javm.ecommerceapi.service;

import com.javm.ecommerceapi.entities.Order;
import com.javm.ecommerceapi.entities.OrderItem;
import com.javm.ecommerceapi.entities.Product;
import com.javm.ecommerceapi.repository.OrderItemRepository;
import com.javm.ecommerceapi.service.orderItem.OrderItemServiceImpl;
import com.javm.ecommerceapi.util.OrderStatus;
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
class OrderItemServiceImplTest {

    @Mock
    private OrderItemRepository orderItemRepository;

    @InjectMocks
    private OrderItemServiceImpl orderItemService;

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
        mockOrderItem.setProduct(mockProduct1);
        mockOrderItem.setOrder(mockOrder1);
        mockOrderItem.setQuantity(2);
    }

    @Test
    void testGetOrderItems() {
        List<OrderItem> mockOrderItems = List.of(mockOrderItem);
        when(orderItemRepository.findAll()).thenReturn(mockOrderItems);

        List<OrderItem> orderItems = orderItemService.getOrderItems();

        assertEquals(1, orderItems.size());
        assertEquals(mockOrderItem, orderItems.get(0));
        verify(orderItemRepository, times(1)).findAll();
    }

    @Test
    void testGetOrderItem() {
        when(orderItemRepository.findById(1L)).thenReturn(Optional.of(mockOrderItem));

        Optional<OrderItem> orderItem = orderItemService.getOrderItem(1L);

        assertTrue(orderItem.isPresent());
        assertEquals(mockOrderItem, orderItem.get());
        verify(orderItemRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateOrderItem() {
        when(orderItemRepository.save(mockOrderItem)).thenReturn(mockOrderItem);

        OrderItem createdOrderItem = orderItemService.createOrderItem(mockOrderItem);

        assertNotNull(createdOrderItem);
        assertEquals(mockOrderItem, createdOrderItem);
        verify(orderItemRepository, times(1)).save(mockOrderItem);
    }

    @Test
    void testUpdateOrderItem() {
        when(orderItemRepository.findById(1L)).thenReturn(Optional.of(mockOrderItem));
        when(orderItemRepository.save(mockOrderItem)).thenReturn(mockOrderItem);

        Optional<OrderItem> updatedOrderItem = orderItemService.updateOrderItem(1L, mockOrderItem);

        assertTrue(updatedOrderItem.isPresent());
        assertEquals(mockOrderItem, updatedOrderItem.get());
        verify(orderItemRepository, times(1)).findById(1L);
        verify(orderItemRepository, times(1)).save(mockOrderItem);
    }

    @Test
    void testDeleteOrderItem() {
        when(orderItemRepository.findById(1L)).thenReturn(Optional.of(mockOrderItem));

        Optional<OrderItem> deletedOrderItem = orderItemService.deleteOrderItem(1L);

        assertTrue(deletedOrderItem.isEmpty());
        verify(orderItemRepository, times(1)).findById(1L);
        verify(orderItemRepository, times(1)).delete(mockOrderItem);
    }
}

