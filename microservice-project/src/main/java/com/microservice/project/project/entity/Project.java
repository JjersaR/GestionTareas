package com.microservice.project.project.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(columnDefinition = "TEXT")
  private String description;

  private Long createdBy;

  @Column(nullable = false, length = 15)
  @Enumerated(EnumType.STRING)
  private EStatus status;

  @CreationTimestamp
  @Column(columnDefinition = "", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(columnDefinition = "", nullable = false)
  private LocalDateTime updatedAt;

  // to update
  public Project(Long id, String name, String description, EStatus status) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.status = status;
  }

  // to save
  public Project(String name, String description, Long createdBy, EStatus status) {
    this.name = name;
    this.description = description;
    this.createdBy = createdBy;
    this.status = status;
  }
}
