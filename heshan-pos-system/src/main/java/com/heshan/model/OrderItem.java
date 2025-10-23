package com.heshan.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItem {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   private Integer quantity;

   private Double price;

   @ManyToOne
   private Product product;

   @ManyToOne
   private Order order;
}
