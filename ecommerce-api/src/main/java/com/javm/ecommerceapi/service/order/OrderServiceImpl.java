package com.javm.ecommerceapi.service.order;

import com.javm.ecommerceapi.entities.Order;
import com.javm.ecommerceapi.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getOrders() {
        log.info("Get all orders");
        return this.orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrder(Long id) {
        log.info("Get an order by ID {}", id);
        return this.orderRepository.findById(id);
    }

    @Override
    public Order createOrder(Order order) {
        log.info("Create a new order: {}", order);
        return this.orderRepository.save(order);
    }

    @Override
    public Optional<Order> updateOrder(Long id, Order order) {
        Optional<Order> orderExist = this.orderRepository.findById(id);

        if (orderExist.isPresent()) {

            log.info("Updating order with id {}", order.getId());
            orderExist.get().setOrderDate(LocalDate.now());
            orderExist.get().setStatus(order.getStatus());

            this.orderRepository.save(orderExist.get());

            return orderExist;
        }

        return Optional.empty();
    }

    @Override
    public Optional<Order> deleteOrder(Long id) {
        Optional<Order> orderExist = this.orderRepository.findById(id);

        if (orderExist.isPresent()) {
            log.info("Deleting order: {}", orderExist.get());
            this.orderRepository.delete(orderExist.get());
        }

        return Optional.empty();
    }
}
