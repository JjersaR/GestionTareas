package com.microservice.student.permits.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Permit {
  // PK
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "permit_ID")
  private Long permitID;

  @Column(unique = true, nullable = false, updatable = false)
  private String name;
}
