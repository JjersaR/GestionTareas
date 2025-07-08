package com.microservice.task.task.dto;

import java.time.LocalDateTime;

import com.microservice.task.task.entity.EStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
  private Long id;

  private String description;

  private Long createdBy;

  private Long projectId; // FK al ID del proyecto

  private Long taskId; // si hay subtareas

  private EStatus status;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}
