package com.heshan.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.heshan.payload.dto.RefundDTO;
import com.heshan.service.RefundService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/refunds")
public class RefundController {

   private final RefundService refundService;

   @PostMapping
   public ResponseEntity<RefundDTO> createRefund(@RequestBody RefundDTO refundDTO) throws Exception {
      RefundDTO createdRefund = refundService.createRefund(refundDTO);
      return ResponseEntity.ok(createdRefund);
   }

   @GetMapping
   public ResponseEntity<List<RefundDTO>> getAllRefunds() throws Exception {
      List<RefundDTO> refunds = refundService.getAllRefunds();
      return ResponseEntity.ok(refunds);
   }

   @GetMapping("/cashier/{cashierId}")
   public ResponseEntity<List<RefundDTO>> getRefundsByCashier(@PathVariable Long cashierId) throws Exception {
      List<RefundDTO> refunds = refundService.getRefundByCashier(cashierId);
      return ResponseEntity.ok(refunds);
   }

   @GetMapping("/branch/{branchId}")
   public ResponseEntity<List<RefundDTO>> getRefundsByBranch(@PathVariable Long branchId) throws Exception {
      List<RefundDTO> refunds = refundService.getRefundsByBranch(branchId);
      return ResponseEntity.ok(refunds);
   }

   @GetMapping("/shift/{shiftId}")
   public ResponseEntity<List<RefundDTO>> getRefundsByShift(@PathVariable Long shiftId) throws Exception {
      List<RefundDTO> refunds = refundService.getRefundByShiftReport(shiftId);
      return ResponseEntity.ok(refunds);
   }

   @GetMapping("/cashier/{cashierId}/range")
   public ResponseEntity<List<RefundDTO>> getRefundsByCashierAndDateRange(@PathVariable Long cashierId,
         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) throws Exception {
      List<RefundDTO> refunds = refundService.getRefundByCashierAndDateRange(cashierId, startDate, endDate);
      return ResponseEntity.ok(refunds);
   }

   @GetMapping("/{id}")
   public ResponseEntity<RefundDTO> getRefundById(@PathVariable Long id) throws Exception {
      RefundDTO refund = refundService.getRefundById(id);
      return ResponseEntity.ok(refund);
   }
}
