package com.heshan.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inventory {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @ManyToOne
   private Branch branch;

   @ManyToOne
   private Product product;

   @Column(nullable = false)
   private Integer quantity;

   private LocalDateTime lastUpdate;

   @PrePersist
   @PreUpdate
   protected void onUpdate() {
      lastUpdate = LocalDateTime.now();
   }
}
