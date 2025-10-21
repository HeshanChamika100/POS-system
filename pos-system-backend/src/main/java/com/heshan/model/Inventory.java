package com.heshan.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
   private Long id;

   @ManyToOne
   private Branch branch;

   @OneToMany
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
