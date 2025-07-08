package com.microservice.task.task.dto;

import com.microservice.task.task.entity.EStatus;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskUpdate {
  private Long id;

  @Nullable
  private String description;

  @Nullable
  private Long taskId; // si hay subtareas

  @Nullable
  private EStatus status;
}
