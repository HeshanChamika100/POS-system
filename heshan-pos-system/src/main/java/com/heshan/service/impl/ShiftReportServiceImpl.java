package com.heshan.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.heshan.domain.PaymentType;
import com.heshan.mapper.ShiftReportMapper;
import com.heshan.model.Branch;
import com.heshan.model.Order;
import com.heshan.model.OrderItem;
import com.heshan.model.PaymentSummary;
import com.heshan.model.Product;
import com.heshan.model.Refund;
import com.heshan.model.ShiftReport;
import com.heshan.model.User;
import com.heshan.payload.dto.ShiftReportDTO;
import com.heshan.repository.OrderRepository;
import com.heshan.repository.RefundRepository;
import com.heshan.repository.ShiftReportRepository;
import com.heshan.repository.UserRepository;
import com.heshan.service.ShiftReportService;
import com.heshan.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShiftReportServiceImpl implements ShiftReportService {

   private final ShiftReportRepository shiftReportRepository;
   private final UserService userService;
   private final RefundRepository refundRepository;
   private final OrderRepository orderRepository;
   private final UserRepository userRepository;

   @Override
   public ShiftReportDTO startShift() throws Exception {

      User currentUser = userService.getCurrentUser();
      LocalDateTime shiftStart = LocalDateTime.now();

      LocalDateTime startOfDay = shiftStart.withHour(0).withMinute(0).withSecond(0);
      LocalDateTime endOfDay = shiftStart.withHour(23).withMinute(59).withSecond(59);
      Optional<ShiftReport> existing = shiftReportRepository.findByCashierIdAndShiftStartBetween(currentUser.getId(),
            startOfDay, endOfDay);
      if (existing.isPresent()) {
         throw new Exception("Shift already started for today");
      }
      Branch branch = currentUser.getBranch();

      ShiftReport shiftReport = ShiftReport.builder().cashier(currentUser).shiftStart(shiftStart).branch(branch)
            .build();
      ShiftReport savedShiftReport = shiftReportRepository.save(shiftReport);
      return ShiftReportMapper.toDTO(savedShiftReport);
   }

   @Override
   public ShiftReportDTO endShift(Long shiftReportId, LocalDateTime shiftEnd) throws Exception {
      User currentUser = userService.getCurrentUser();
      ShiftReport shiftReport = shiftReportRepository
            .findTopByCashierAndShiftEndIsNullOrderByShiftStartDesc(currentUser)
            .orElseThrow(() -> new Exception("Shift not found"));
      shiftReport.setShiftEnd(shiftEnd);
      List<Refund> refunds = refundRepository.findByCashierIdAndCreatedAtBetween(currentUser.getId(),
            shiftReport.getShiftStart(), shiftReport.getShiftEnd());
      double totalRefunds = refunds.stream()
            .mapToDouble(refund -> refund.getAmount() != null ? refund.getAmount() : 0.0).sum();
      List<Order> orders = orderRepository.findByCashierAndCreatedAtBetween(currentUser, shiftReport.getShiftStart(),
            shiftReport.getShiftEnd());
      double totalSales = orders.stream().mapToDouble(Order::getTotalAmount).sum();
      int totalOrders = orders.size();
      double netSales = totalSales - totalRefunds;
      shiftReport.setTotalRefunds(totalRefunds);
      shiftReport.setTotalSales(totalSales);
      shiftReport.setTotalOrders(totalOrders);
      shiftReport.setNetSale(netSales);
      shiftReport.setRecentOrders(getRecentOrders(orders));
      shiftReport.setTopSellingProducts(getTopSellingProducts(orders));
      shiftReport.setPaymentSummaries(getPaymentSummaries(orders, totalSales));
      shiftReport.setRefunds(refunds);

      ShiftReport savedShiftReport = shiftReportRepository.save(shiftReport);
      return ShiftReportMapper.toDTO(savedShiftReport);
   }

   @Override
   public ShiftReportDTO getShiftReportById(Long shiftReportId) throws Exception {
      return shiftReportRepository.findById(shiftReportId).map(ShiftReportMapper::toDTO)
            .orElseThrow(() -> new Exception("Shift report not found with given id: " + shiftReportId));
   }

   @Override
   public List<ShiftReportDTO> getAllShiftReports() {
      List<ShiftReport> reports = shiftReportRepository.findAll();
      return reports.stream().map(ShiftReportMapper::toDTO).collect(Collectors.toList());
   }

   @Override
   public List<ShiftReportDTO> getShiftReportsByBranchId(Long branchId) {
      return shiftReportRepository.findByBranchId(branchId).stream()
            .map(ShiftReportMapper::toDTO)
            .collect(Collectors.toList());
   }

   @Override
   public List<ShiftReportDTO> getShiftReportsByCashierId(Long cashierId) {
      return shiftReportRepository.findByCashierId(cashierId).stream()
            .map(ShiftReportMapper::toDTO)
            .collect(Collectors.toList());
   }

   @Override
   public ShiftReportDTO getCurrentShiftProgress(Long cashierId) throws Exception {
      User user = userService.getCurrentUser();
      ShiftReport shiftReport =  shiftReportRepository.findTopByCashierAndShiftEndIsNullOrderByShiftStartDesc(user).orElseThrow(() -> new Exception("Current shift not found"));
      LocalDateTime now = LocalDateTime.now();
      List<Order> orders = orderRepository.findByCashierAndCreatedAtBetween(user, shiftReport.getShiftStart(), now);
      List<Refund> refunds = refundRepository.findByCashierIdAndCreatedAtBetween(user.getId(),
            shiftReport.getShiftStart(), now);
      double totalRefunds = refunds.stream()
            .mapToDouble(refund -> refund.getAmount() != null ? refund.getAmount() : 0.0).sum();
      
      double totalSales = orders.stream().mapToDouble(Order::getTotalAmount).sum();
      int totalOrders = orders.size();
      double netSales = totalSales - totalRefunds;
      shiftReport.setTotalRefunds(totalRefunds);
      shiftReport.setTotalSales(totalSales);
      shiftReport.setTotalOrders(totalOrders);
      shiftReport.setNetSale(netSales);
      shiftReport.setRecentOrders(getRecentOrders(orders));
      shiftReport.setTopSellingProducts(getTopSellingProducts(orders));
      shiftReport.setPaymentSummaries(getPaymentSummaries(orders, totalSales));
      shiftReport.setRefunds(refunds);

      ShiftReport savedReport = shiftReportRepository.save(shiftReport);

      return ShiftReportMapper.toDTO(savedReport);
   }

   @Override
   public ShiftReportDTO getShiftByCashierAndDate(Long cashierId, LocalDateTime date) throws Exception {
      User cashier = userRepository.findById(cashierId).orElseThrow(() -> new Exception("Cashier not found with given id: "+ cashierId));
      LocalDateTime start = date.withHour(0).withMinute(0).withSecond(0);
      LocalDateTime end = date.withHour(23).withMinute(59).withSecond(59);

      ShiftReport report = shiftReportRepository.findByCashierIdAndShiftStartBetween(cashierId, start, end).orElseThrow(() -> new Exception("Shift Report not found for date: "+ date));
      return ShiftReportMapper.toDTO(report);
   }

   // Helper Methods

   private List<PaymentSummary> getPaymentSummaries(List<Order> orders, double totalSales) {
      Map<PaymentType, List<Order>> grouped = orders.stream()
            .collect(Collectors
                  .groupingBy(order -> order.getPaymentType() != null ? order.getPaymentType() : PaymentType.CASH));

      List<PaymentSummary> summaries = new ArrayList<>();
      for (Map.Entry<PaymentType, List<Order>> entry : grouped.entrySet()) {
         double amount = entry.getValue().stream()
               .mapToDouble(Order::getTotalAmount)
               .sum();
         int transactions = entry.getValue().size();
         double percentage = (amount / totalSales) * 100;
         PaymentSummary summary = new PaymentSummary();
         summary.setPaymentType(entry.getKey());
         summary.setTotalAmount(amount);
         summary.setPercentage(percentage);
         summary.setTransactionCount(transactions);
         summaries.add(summary);
      }
      return summaries;
   }

   private List<Product> getTopSellingProducts(List<Order> orders) {
      Map<Product, Integer> productSalesMap = new HashMap<>();
      for (Order order : orders) {
         for (OrderItem item : order.getItems()) {
            Product product = item.getProduct();
            productSalesMap.put(product, productSalesMap.getOrDefault(product, 0) + item.getQuantity());
         }
      }
      return productSalesMap.entrySet().stream()
            .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
            .limit(5)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
   }

   private List<Order> getRecentOrders(List<Order> orders) {
      return orders.stream().sorted(Comparator.comparing(Order::getCreatedAt).reversed()).limit(5)
            .collect(Collectors.toList());
   }
}
