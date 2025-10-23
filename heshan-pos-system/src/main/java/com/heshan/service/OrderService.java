package com.heshan.service;

import java.util.List;

import com.heshan.domain.OrderStatus;
import com.heshan.domain.PaymentType;
import com.heshan.payload.dto.OrderDTO;

public interface OrderService {

   OrderDTO createOrder(OrderDTO orderDTO) throws Exception;

   OrderDTO getOrderById(Long id) throws Exception;

   List<OrderDTO> getOrdersByBranch(Long branchId, Long customerId, Long cashierId, PaymentType paymentType,
         OrderStatus status) throws Exception;

   List<OrderDTO> getRecentOrdersByCashier(Long cashierId) throws Exception;

   void deleteOrder(Long id) throws Exception;

   List<OrderDTO> getTodayOrdersByBranch(Long branchId) throws Exception;

   List<OrderDTO> getOrdersByCustomerId(Long customerId) throws Exception;

   List<OrderDTO> getTop5RecentOrdersByBranchId(Long branchId) throws Exception;
}
