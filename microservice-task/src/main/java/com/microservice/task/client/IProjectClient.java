package com.microservice.task.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "msvc-project", url = "localhost:8080/api/project")
public interface IProjectClient {

}
