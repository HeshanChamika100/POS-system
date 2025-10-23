package com.heshan.payload.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.heshan.domain.PaymentType;
import com.heshan.model.Customer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDTO {

   private Long id;

   private Double totalAmount;

   private LocalDateTime createdAt;

   private Long branchId;
   private Long customerId;

   private BranchDTO branch;

   private UserDto cashier;

   private Customer customer;

   private PaymentType paymentType;

   private List<OrderItemDTO> items;
}
