package com.microservice.project.client.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

  private Long id;

  private String description;

  private Long createdBy;

  private UserDTO user;

  private Long projectId;

  private Long taskId;

  private String status;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}
