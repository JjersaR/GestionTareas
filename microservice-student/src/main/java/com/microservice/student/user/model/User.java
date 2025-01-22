package com.microservice.student.user.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.microservice.student.roles.model.Role;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String name;

  @Column(unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @CreationTimestamp
  @Column(columnDefinition = "", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  // Security
  @Column(name = "is_enabled")
  private boolean isEnabled;

  @Column(name = "account_no_expired")
  private boolean accountNoExpired;

  @Column(name = "account_no_locked")
  private boolean accountNoLocked;

  @Column(name = "credential_no_expired")
  private boolean credentialNoExpired;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "User_ID"), inverseJoinColumns = @JoinColumn(name = "Rol_ID"))
  private Set<Role> roles = new HashSet<>();
}
