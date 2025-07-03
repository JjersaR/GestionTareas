package com.microservice.project.project.http.response;

import java.time.LocalDateTime;

import com.microservice.project.client.dto.UserDTO;
import com.microservice.project.project.entity.EStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInProjectResponse {
  private String nameProject;

  private String descriptionProject;

  private UserDTO created;

  private EStatus status;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}
