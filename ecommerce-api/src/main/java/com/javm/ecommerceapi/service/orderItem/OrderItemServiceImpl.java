package com.javm.ecommerceapi.service.orderItem;

import com.javm.ecommerceapi.entities.OrderItem;
import com.javm.ecommerceapi.repository.OrderItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public List<OrderItem> getOrderItems() {
        log.info("Get all order items");
        return this.orderItemRepository.findAll();
    }

    @Override
    public Optional<OrderItem> getOrderItem(Long id) {
        log.info("Get an order item by ID {}", id);
        return this.orderItemRepository.findById(id);
    }

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        log.info("Create a new order item: {}", orderItem);
        return this.orderItemRepository.save(orderItem);
    }

    @Override
    public Optional<OrderItem> updateOrderItem(Long id, OrderItem orderItem) {
        Optional<OrderItem> orderItemExist = this.orderItemRepository.findById(id);

        if (orderItemExist.isPresent()) {

            log.info("Updating order item with id {}", orderItem.getId());
            orderItemExist.get().setOrder(orderItem.getOrder());
            orderItemExist.get().setProduct(orderItem.getProduct());
            orderItemExist.get().setQuantity(orderItem.getQuantity());

            this.orderItemRepository.save(orderItemExist.get());

            return orderItemExist;
        }

        return Optional.empty();
    }

    @Override
    public Optional<OrderItem> deleteOrderItem(Long id) {
        Optional<OrderItem> orderItemExist = this.orderItemRepository.findById(id);

        if (orderItemExist.isPresent()) {
            log.info("Deleting order: {}", orderItemExist.get());
            this.orderItemRepository.delete(orderItemExist.get());
        }

        return Optional.empty();
    }
}
