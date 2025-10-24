package com.heshan.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heshan.model.Refund;

public interface RefundRepository extends JpaRepository<Refund, Long> {

   List<Refund> findByCashierIdAndCreatedAtBetween(Long cashierId, LocalDateTime from, LocalDateTime to);
   List<Refund> findByCashierId(Long cashierId);
   List<Refund> findByShiftReportId(Long shiftReportId);
   List<Refund> findByBranchId(Long branchId);
}
