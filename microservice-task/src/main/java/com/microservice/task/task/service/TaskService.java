package com.microservice.task.task.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.task.task.entity.Task;
import com.microservice.task.task.repository.ITaskRepository;

@Service
public class TaskService {

  @Autowired
  private ITaskRepository repository;

  public Optional<Task> findById(Long id) {
    return repository.findById(id);
  }

  public List<Task> findByProjectId(Long id) {
    var tasks = repository.findByProjectId(id);
    return (tasks.isEmpty()) ? null : tasks;
  }

  public List<Task> findByTaskId(Long id) {
    var tasks = repository.findByTaskId(id);
    return (tasks.isEmpty()) ? null : tasks;
  }

  public void save(Task task) {
    repository.save(task);
  }

  public void update(Task task) {
    repository.update(task);
  }

  public void deleteById(Long id) {
    if (findById(id).get() != null) {
      repository.deleteById(id);
    }
  }
}
