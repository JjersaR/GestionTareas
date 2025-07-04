package com.microservice.task.task.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.microservice.task.task.entity.Task;

import jakarta.transaction.Transactional;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Long> {

  Optional<Task> findById(Long id);

  List<Task> findByProjectId(Long id);

  @Transactional
  @Modifying
  @Query("UPDATE Task t SET t.description = COALESCE(:#{#task.description}, t.description), t.status = COALESCE(:#{#task.status}, t.status), t.taskId = COALESCE(:#{#task.taskId}, t.taskId) WHERE t.id = :#{#task.id}")
  void update(Task task);
}
