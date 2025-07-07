package com.microservice.project.project.http.response;

import java.time.LocalDateTime;
import java.util.List;

import com.microservice.project.project.entity.EStatus;
import com.microservice.project.client.dto.TaskDTO;
import com.microservice.project.client.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TasksInProjectResponse {
  private Long id;

  private String name;

  private String description;

  private List<TaskDTO> tasks;

  private UserDTO createdBy;

  private EStatus status;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}
