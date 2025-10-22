package com.heshan.model;

import java.time.LocalDateTime;

import com.heshan.domain.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @Column(nullable = false)
   private String fullName;

   @Column(nullable = false, unique = true)
   @Email(message = "Email should be valid")
   private String email;

   @ManyToOne
   private Store store;

   @ManyToOne
   private Branch branch;

   private String phone;

   @Column(nullable = false)
   private UserRole role;

   @Column(nullable = false)
   private String password;

   private LocalDateTime createdAt;
   private LocalDateTime updatedAt;
   private LocalDateTime lastLogin;
}
