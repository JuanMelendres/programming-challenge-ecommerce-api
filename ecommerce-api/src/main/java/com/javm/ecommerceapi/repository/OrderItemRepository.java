package com.javm.ecommerceapi.repository;

import com.javm.ecommerceapi.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {}

