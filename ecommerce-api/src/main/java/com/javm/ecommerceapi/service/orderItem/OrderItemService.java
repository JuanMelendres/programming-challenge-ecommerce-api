package com.javm.ecommerceapi.service.orderItem;

import com.javm.ecommerceapi.entities.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderItemService {
    public List<OrderItem> getOrderItems();
    public Optional<OrderItem> getOrderItem(Long id);
    public OrderItem createOrderItem(OrderItem orderItem);
    public Optional<OrderItem> updateOrderItem(Long id, OrderItem orderItem);
    public Optional<OrderItem> deleteOrderItem(Long id);
}
