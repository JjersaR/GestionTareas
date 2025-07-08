package com.microservice.task.task.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.task.task.dto.TaskDTO;
import com.microservice.task.task.dto.TaskRequest;
import com.microservice.task.task.dto.TaskUpdate;
import com.microservice.task.task.entity.Task;
import com.microservice.task.task.repository.ITaskRepository;

@Service
public class TaskService {

  @Autowired
  private ITaskRepository repository;

  // to list task
  private TaskDTO convertToDTO(Task task) {
    return new TaskDTO(task.getId(), task.getDescription(), task.getCreatedBy(), task.getProjectId(), task.getTaskId(),
        task.getStatus(), task.getCreatedAt(), task.getUpdatedAt());
  }

  // to save
  private Task convertToEntity(TaskRequest request) {
    return new Task(request.getDescription(), request.getCreatedBy(), request.getProjectId(), request.getTaskId(),
        request.getStatus());
  }

  // to update
  private Task convertToEntityUpdate(TaskUpdate taskUpdate) {
    return new Task(taskUpdate.getId(), taskUpdate.getDescription(), taskUpdate.getTaskId(), taskUpdate.getStatus());
  }

  public Optional<TaskDTO> findById(Long id) {
    var task = repository.findById(id);
    return (task.isPresent()) ? Optional.of(convertToDTO(task.get())) : Optional.empty();
  }

  public List<TaskDTO> findByProjectId(Long id) {
    var tasks = repository.findByProjectId(id);
    return tasks.stream().map(this::convertToDTO).collect(Collectors.toList());
  }

  public List<TaskDTO> findByTaskId(Long id) {
    var tasks = repository.findByTaskId(id);
    return tasks.stream().map(this::convertToDTO).collect(Collectors.toList());
  }

  public void save(TaskRequest task) {
    repository.save(convertToEntity(task));
  }

  public void update(TaskUpdate task) {
    repository.update(convertToEntityUpdate(task));
  }

  public void deleteById(Long id) {
    if (findById(id).get() != null) {
      repository.deleteById(id);
    }
  }
}
