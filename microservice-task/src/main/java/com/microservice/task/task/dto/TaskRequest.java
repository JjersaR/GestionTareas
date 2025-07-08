package com.microservice.task.task.dto;

import com.microservice.task.task.entity.EStatus;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskRequest {
  @NotBlank
  private String description;

  @NotNull(message = "This field cannot be empty.")
  private Long createdBy;

  @NotNull(message = "This field cannot be empty.")
  private Long projectId; // FK al ID del proyecto

  @Nullable
  private Long taskId; // si hay subtareas

  @Nullable
  private EStatus status = EStatus.PENDING;
}
