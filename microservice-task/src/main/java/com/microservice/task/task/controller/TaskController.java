package com.microservice.task.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.task.task.service.TaskService;

@RestController
@RequestMapping("/api/task")
public class TaskController {

  @Autowired
  private TaskService service;

  @GetMapping
  public ResponseEntity<?> findById(@RequestParam Long id) {
    var tasks = service.findById(id).get();
    return (tasks != null) ? ResponseEntity.ok(tasks) : ResponseEntity.noContent().build();
  }

  @GetMapping("/project")
  public ResponseEntity<?> findByProjectId(@RequestParam Long id) {
    var tasks = service.findByProjectId(id);
    return (!tasks.isEmpty()) ? ResponseEntity.ok(tasks) : ResponseEntity.noContent().build();
  }

}
