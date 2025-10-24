package com.heshan.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.heshan.mapper.RefundMapper;
import com.heshan.model.Branch;
import com.heshan.model.Order;
import com.heshan.model.Refund;
import com.heshan.model.User;
import com.heshan.payload.dto.RefundDTO;
import com.heshan.repository.OrderRepository;
import com.heshan.repository.RefundRepository;
import com.heshan.service.RefundService;
import com.heshan.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefundServiceImpl implements RefundService {

   private final RefundRepository refundRepository;
   private final UserService userService;
   private final OrderRepository orderRepository;

   @Override
   public RefundDTO createRefund(RefundDTO refund) throws Exception {

      User cashier = userService.getCurrentUser();
      Order order = orderRepository.findById(refund.getOrderId()).orElseThrow(() -> new Exception("Order not found"));
      Branch branch = order.getBranch();

      Refund createdRefund = Refund.builder().order(order).cashier(cashier).branch(branch).reason(refund.getReason())
            .amount(refund.getAmount()).createdAt(refund.getCreatedAt()).build();
      Refund savedRefund = refundRepository.save(createdRefund);
      return RefundMapper.toDTO(savedRefund);
   }

   @Override
   public List<RefundDTO> getAllRefunds() throws Exception {

      return refundRepository.findAll().stream().map(RefundMapper::toDTO).collect(Collectors.toList());
   }

   @Override
   public List<RefundDTO> getRefundByCashier(Long cashierId) throws Exception {
      return refundRepository.findByCashierId(cashierId).stream().map(RefundMapper::toDTO).collect(Collectors.toList());
   }

   @Override
   public List<RefundDTO> getRefundByShiftReport(Long shiftReportId) throws Exception {
      return refundRepository.findByShiftReportId(shiftReportId).stream().map(RefundMapper::toDTO)
            .collect(Collectors.toList());
   }

   @Override
   public List<RefundDTO> getRefundByCashierAndDateRange(Long cashierId, LocalDateTime startDate, LocalDateTime endDate)
         throws Exception {
      return refundRepository.findByCashierIdAndCreatedAtBetween(cashierId, startDate, endDate).stream()
            .map(RefundMapper::toDTO).collect(Collectors.toList());
   }

   @Override
   public List<RefundDTO> getRefundsByBranch(Long branchId) throws Exception {
      return refundRepository.findByBranchId(branchId).stream().map(RefundMapper::toDTO).collect(Collectors.toList());
   }

   @Override
   public RefundDTO getRefundById(Long refundId) throws Exception {
      return refundRepository.findById(refundId).map(RefundMapper::toDTO)
            .orElseThrow(() -> new Exception("Refund not found"));
   }

   @Override
   public void deleteRefund(Long refundId) throws Exception {
      this.getRefundById(refundId);
      refundRepository.deleteById(refundId);
   }
}
