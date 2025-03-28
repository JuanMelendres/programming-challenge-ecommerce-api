package com.javm.ecommerceapi.service.order;

import com.javm.ecommerceapi.entities.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    public List<Order> getOrders();
    public Optional<Order> getOrder(Long id);
    public Order createOrder(Order order);
    public Optional<Order> updateOrder(Long id, Order order);
    public Optional<Order> deleteOrder(Long id);
}
