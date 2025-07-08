package com.microservice.user.user.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserUpdate {
  @Nullable
  @Pattern(regexp = "^[A-Z][a-z]{2,}$", message = "The field must begin with an uppercase letter followed by lowercase letters and must have at least 3 letters.")
  private String name;

  @Nullable
  @Email
  private String email;
}
