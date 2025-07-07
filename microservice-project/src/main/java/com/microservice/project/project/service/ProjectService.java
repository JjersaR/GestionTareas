package com.microservice.project.project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.project.client.ITaskClient;
import com.microservice.project.client.IUserClient;
import com.microservice.project.client.dto.TaskDTO;
import com.microservice.project.client.dto.UserDTO;
import com.microservice.project.project.entity.Project;
import com.microservice.project.project.http.response.TasksInProjectResponse;
import com.microservice.project.project.http.response.UserInProjectResponse;
import com.microservice.project.project.repository.IProjectRepository;

@Service
public class ProjectService {

  @Autowired
  private IProjectRepository repository;

  @Autowired
  private IUserClient userClient;

  @Autowired
  private ITaskClient taskClient;

  public List<UserInProjectResponse> findByUserId(Long id) {
    var projects = repository.findByCreatedBy(id);
    var userOp = userClient.findUserById(id);

    if (userOp == null) {
      return null;
    }
    var userEnt = userOp.get();

    var user = UserDTO.builder().name(userEnt.getName()).email(userEnt.getEmail()).build();

    return projects.stream()
        .map(project -> mapToUserInProjectResponse(project, user))
        .collect(Collectors.toList());
  }

  private UserInProjectResponse mapToUserInProjectResponse(Project project, UserDTO user) {
    return UserInProjectResponse.builder()
        .nameProject(project.getName())
        .descriptionProject(project.getDescription())
        .created(user) // UserDTO obtenido de Feign
        .status(project.getStatus())
        .createdAt(project.getCreatedAt())
        .updatedAt(project.getUpdatedAt())
        .build();
  }

  public TasksInProjectResponse findById(Long id) {
    // 1. Obtener el proyecto
    var projectOp = repository.findById(id);
    if (projectOp.isEmpty()) {
      return null;
    }
    Project project = projectOp.get();

    // 2. Obtener el usuario creador
    var userOp = userClient.findUserById(project.getCreatedBy());
    if (userOp.isEmpty()) {
      return null;
    }

    UserDTO userEnt = userOp.get();
    UserDTO user = UserDTO.builder()
        .name(userEnt.getName())
        .email(userEnt.getEmail())
        .build();

    // 3. Obtener las tareas del proyecto
    List<TaskDTO> tasks = taskClient.findByProjectId(id);

    tasks.forEach(task -> {
      task.setUser(userClient.findUserById(task.getCreatedBy()).get());
    });

    // 4. Mapear a la respuesta final
    return mapToTasksInProjectResponse(project, user, tasks);
  }

  private TasksInProjectResponse mapToTasksInProjectResponse(Project project, UserDTO user, List<TaskDTO> tasks) {
    return TasksInProjectResponse.builder()
        .id(project.getId())
        .name(project.getName())
        .description(project.getDescription())
        .tasks(tasks) // TaskDTO obtenido de Feign
        .createdBy(user) // UserDTO obtenido de Feign
        .status(project.getStatus())
        .createdAt(project.getCreatedAt())
        .updatedAt(project.getUpdatedAt())
        .build();
  }

  public void save(Project project) {
    repository.save(project);
  }

  public void update(Project project) {
    repository.update(project);
  }

  public void deleteById(Long id) {
    if (findById(id) != null) {
      repository.deleteById(id);
    }
  }
}
