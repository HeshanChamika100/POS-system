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
public class ShiftReport {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   
}
