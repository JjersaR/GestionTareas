package com.microservice.project.project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.project.client.IUserClient;
import com.microservice.project.client.dto.UserDTO;
import com.microservice.project.project.entity.Project;
import com.microservice.project.project.http.response.UserInProjectResponse;
import com.microservice.project.project.repository.IProjectRepository;

@Service
public class ProjectService {

  @Autowired
  private IProjectRepository repository;

  @Autowired
  private IUserClient client;

  public List<UserInProjectResponse> findByUserId(Long id) {
    var projects = repository.findByCreatedBy(id);
    var userOp = client.findUserById(id);

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

  public Project findById(Long id) {
    var project = repository.findById(id);
    return (project.isPresent()) ? project.get() : null;
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
