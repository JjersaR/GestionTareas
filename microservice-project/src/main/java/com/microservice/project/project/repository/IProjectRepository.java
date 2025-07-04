package com.microservice.project.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.microservice.project.project.entity.Project;

import jakarta.transaction.Transactional;

@Repository
public interface IProjectRepository extends JpaRepository<Project, Long> {

  // Projects by User id
  List<Project> findByCreatedBy(Long id);

  // Project by id
  Optional<Project> findById(Long id);

  // update project
  @Transactional
  @Modifying
  @Query("UPDATE Project p SET p.name = COALESCE(:#{#proj.name}, p.name), p.description = COALESCE(:#{#proj.description}, p.description), p.status = COALESCE(:#{#proj.status}, p.status) WHERE p.id = :#{#proj.id}")
  void update(Project proj);
}
