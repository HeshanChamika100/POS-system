package com.heshan.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heshan.model.ShiftReport;
import com.heshan.model.User;

public interface ShiftReportRepository extends JpaRepository<ShiftReport, Long> {

   List<ShiftReport> findByCashierId(Long cashierId);

   List<ShiftReport> findByBranchId(Long branchId);

   Optional<ShiftReport> findTopByCashierAndShiftEndIsNullOrderByShiftStartDesc(User cashier);

   Optional<ShiftReport> findByCashierIdAndShiftStartBetween(Long cashierId, LocalDateTime start, LocalDateTime end);
}
