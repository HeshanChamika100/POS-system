package com.heshan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heshan.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
