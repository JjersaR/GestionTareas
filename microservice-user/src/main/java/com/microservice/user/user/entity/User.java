package com.microservice.user.user.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String name;

  @Column(unique = true)
  private String email;

  @CreationTimestamp
  @Column(columnDefinition = "", nullable = false, updatable = false)
  private LocalDateTime createdAt;
}
