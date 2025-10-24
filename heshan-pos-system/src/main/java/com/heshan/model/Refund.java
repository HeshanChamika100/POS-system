package com.heshan.model;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.heshan.domain.PaymentType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Refund {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @ManyToOne
   private Order order;

   private String reason;
   
   private Double amount;

   @ManyToOne
   @JsonIgnore
   private ShiftReport shiftReport;

   @ManyToOne
   private User cashier;

   @ManyToOne
   private Branch branch;

   private PaymentType paymentType;

   private LocalDateTime createdAt;

   @PrePersist
   protected void onCreate() {
      createdAt = LocalDateTime.now();
   }
}
