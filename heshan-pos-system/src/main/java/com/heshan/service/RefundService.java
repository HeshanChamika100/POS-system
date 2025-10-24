package com.heshan.service;

import java.time.LocalDateTime;
import java.util.List;

import com.heshan.model.Refund;
import com.heshan.payload.dto.RefundDTO;

public interface RefundService {

   RefundDTO createRefund(RefundDTO refund) throws Exception;
   List<RefundDTO> getAllRefunds() throws Exception;
   List<RefundDTO> getRefundByCashier(Long cashierId) throws Exception;
   List<RefundDTO> getRefundByShiftReport(Long shiftReportId) throws Exception;
   List<RefundDTO> getRefundByCashierAndDateRange(Long cashierId, LocalDateTime startDate, LocalDateTime endDate) throws Exception;
   List<RefundDTO> getRefundsByBranch(Long branchId) throws Exception;
   RefundDTO getRefundById(Long refundId) throws Exception;
   void deleteRefund(Long refundId) throws Exception;
}
