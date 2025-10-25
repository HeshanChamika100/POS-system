package com.heshan.service;

import java.time.LocalDateTime;
import java.util.List;

import com.heshan.payload.dto.ShiftReportDTO;

public interface ShiftReportService {

   ShiftReportDTO startShift() throws Exception;
   ShiftReportDTO endShift(Long shiftReportId, LocalDateTime shiftEnd) throws Exception;
   ShiftReportDTO getShiftReportById(Long shiftReportId) throws Exception;
   List<ShiftReportDTO> getAllShiftReports();
   List<ShiftReportDTO> getShiftReportsByBranchId(Long branchId);
   List<ShiftReportDTO> getShiftReportsByCashierId(Long cashierId);
   ShiftReportDTO getCurrentShiftProgress(Long cashierId) throws Exception;
   ShiftReportDTO getShiftByCashierAndDate(Long cashierId, LocalDateTime date) throws Exception;
}
