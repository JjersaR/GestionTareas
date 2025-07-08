package com.microservice.project.project.dto;

import com.microservice.project.project.entity.EStatus;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ProjectUpdate {
  @Setter
  @NotNull
  private Long id;

  @Nullable
  @Pattern(regexp = "^[A-Z][a-z]{2,}$", message = "The field must begin with an uppercase letter followed by lowercase letters and must have at least 3 letters.")
  private String name;

  @Nullable
  @Pattern(regexp = "^[A-Z][a-z]{2,}$", message = "The field must begin with an uppercase letter followed by lowercase letters and must have at least 3 letters.")
  private String description;

  @Nullable
  private EStatus status;
}
