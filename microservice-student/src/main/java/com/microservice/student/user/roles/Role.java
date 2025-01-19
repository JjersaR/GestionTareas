package com.microservice.student.user.roles;

import java.util.HashSet;
import java.util.Set;

import com.microservice.student.user.permits.Permit;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Role {
  // PK
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Rol_ID")
  private Long RolID;

  @Column(nullable = false, length = 50, unique = true)
  @Enumerated(EnumType.STRING)
  private ERole name;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(name = "roles_permits", joinColumns = @JoinColumn(name = "Rol_ID"), inverseJoinColumns = @JoinColumn(name = "Permit_ID"))
  private Set<Permit> permitsList = new HashSet<>();
}
