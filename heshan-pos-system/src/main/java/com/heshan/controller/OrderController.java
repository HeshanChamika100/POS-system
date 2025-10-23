package com.heshan.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.heshan.domain.OrderStatus;
import com.heshan.domain.PaymentType;
import com.heshan.payload.dto.OrderDTO;
import com.heshan.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

   private final OrderService orderService;

   @PostMapping
   public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) throws Exception {
      OrderDTO createdOrder = orderService.createOrder(orderDTO);
      return ResponseEntity.ok(createdOrder);
   }

   @GetMapping("/{id}")
   public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) throws Exception {
      return ResponseEntity.ok(orderService.getOrderById(id));
   }

   @GetMapping("/branch/{branchId}")
   public ResponseEntity<List<OrderDTO>> getOrdersByBranch(@PathVariable Long branchId,
         @RequestParam(required = false) Long customerId, @RequestParam(required = false) Long cashierId,
         @RequestParam(required = false) PaymentType paymentType, @RequestParam(required = false) OrderStatus status)
         throws Exception {
      return ResponseEntity.ok(orderService.getOrdersByBranch(branchId, customerId, cashierId, paymentType, status));
   }

   @GetMapping("/cashier/{id}")
   public ResponseEntity<List<OrderDTO>> getOrdersByCashier(@PathVariable Long id) throws Exception {
      return ResponseEntity.ok(orderService.getRecentOrdersByCashier(id));
   }

   @GetMapping("/today/branch/{id}")
   public ResponseEntity<List<OrderDTO>> getTodayOrders(@PathVariable Long id) throws Exception {
      return ResponseEntity.ok(orderService.getTodayOrdersByBranch(id));
   }

   @GetMapping("/customer/{id}")
   public ResponseEntity<List<OrderDTO>> getCustomersOrders(@PathVariable Long id) throws Exception {
      return ResponseEntity.ok(orderService.getOrdersByCustomerId(id));
   }

   @GetMapping("/recent/{branchId}")
   public ResponseEntity<List<OrderDTO>> getRecentOrders(@PathVariable Long branchId) throws Exception {
      return ResponseEntity.ok(orderService.getTop5RecentOrdersByBranchId(branchId));
   }
}
