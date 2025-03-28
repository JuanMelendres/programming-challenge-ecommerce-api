package com.javm.ecommerceapi.repository;

import com.javm.ecommerceapi.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {}

