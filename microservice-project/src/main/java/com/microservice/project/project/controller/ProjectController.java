package com.microservice.project.project.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.project.project.entity.Project;
import com.microservice.project.project.service.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

  @Autowired
  private ProjectService service;

  @GetMapping("/by")
  public ResponseEntity<?> getProject(@RequestParam(required = false, name = "user") Long idUser,
      @RequestParam(required = false, name = "id") Long idProject) {

    if (idUser != null) {
      var byUser = service.findByUserId(idUser);
      return (byUser != null) ? ResponseEntity.ok(byUser) : ResponseEntity.notFound().build();
    } else if (idProject != null) {
      var byProject = service.findById(idProject);
      return (byProject != null) ? ResponseEntity.ok(byProject) : ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.badRequest().body("Debe proporcionar 'user' o 'project' como parámetro");
    }

  }

  @PostMapping
  public ResponseEntity<?> save(@RequestBody Project project) throws URISyntaxException {
    service.save(project);
    return ResponseEntity.created(new URI("/api/project")).build();
  }

  @PutMapping
  public ResponseEntity<?> update(@RequestParam Long id, @RequestBody Project project) {
    project.setId(id);
    service.update(project);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping
  public ResponseEntity<?> deleteById(@RequestParam Long id) {
    service.deleteById(id);
    return ResponseEntity.ok().build();
  }
}
