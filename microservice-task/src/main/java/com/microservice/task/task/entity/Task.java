package com.microservice.task.task.entity;

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
public class Task {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "TEXT")
  private String description;

  private Long createdBy;

  private Long projectId; // FK al ID del proyecto

  private Long taskId; // si hay subtareas

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
  public Task(Long id, String description, Long taskId, EStatus status) {
    this.id = id;
    this.description = description;
    this.taskId = taskId;
    this.status = status;
  }

  // to save
  public Task(String description, Long createdBy, Long projectId, Long taskId, EStatus status) {
    this.description = description;
    this.createdBy = createdBy;
    this.projectId = projectId;
    this.taskId = taskId;
    this.status = status;
  }
}
