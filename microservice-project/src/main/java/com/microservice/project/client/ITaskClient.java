package com.microservice.project.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.microservice.project.client.dto.TaskDTO;

@FeignClient(name = "msvc-task", url = "msvc-gateway:8080/api/task")
public interface ITaskClient {
  @GetMapping("/project")
  List<TaskDTO> findByProjectId(@RequestParam Long id);
}
