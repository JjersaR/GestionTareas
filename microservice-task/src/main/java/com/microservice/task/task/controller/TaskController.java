package com.microservice.task.task.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.task.task.dto.TaskRequest;
import com.microservice.task.task.dto.TaskUpdate;
import com.microservice.task.task.service.TaskService;

@RestController
@RequestMapping("/api/task")
public class TaskController {

  @Autowired
  private TaskService service;

  @GetMapping
  public ResponseEntity<?> findById(@RequestParam Long id) {
    var tasks = service.findById(id);
    return (!tasks.isEmpty()) ? ResponseEntity.ok(tasks.get()) : ResponseEntity.noContent().build();
  }

  @GetMapping("/sub")
  public ResponseEntity<?> subTask(@RequestParam Long id) {
    var tasks = service.findByTaskId(id);
    return (!tasks.isEmpty() || tasks != null) ? ResponseEntity.ok(tasks) : ResponseEntity.noContent().build();
  }

  @GetMapping("/project")
  public ResponseEntity<?> findByProjectId(@RequestParam Long id) {
    var tasks = service.findByProjectId(id);
    return (!tasks.isEmpty()) ? ResponseEntity.ok(tasks) : ResponseEntity.noContent().build();
  }

  @PostMapping
  public ResponseEntity<?> save(@RequestBody @Validated TaskRequest task) throws URISyntaxException {
    service.save(task);
    return ResponseEntity.created(new URI("/api/task")).build();
  }

  @PutMapping
  public ResponseEntity<?> update(@RequestParam Long id, @RequestBody @Validated TaskUpdate task) {
    task.setId(id);
    service.update(task);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping
  public ResponseEntity<?> deleteById(@RequestParam Long id) {
    service.deleteById(id);
    return ResponseEntity.ok().build();
  }

}
