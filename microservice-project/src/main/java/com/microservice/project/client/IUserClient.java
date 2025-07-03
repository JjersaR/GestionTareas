package com.microservice.project.client;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.microservice.project.client.dto.UserDTO;

@FeignClient(name = "msvc-user", url = "localhost:8080/api/user")
public interface IUserClient {

  @GetMapping("/by")
  Optional<UserDTO> findUserById(@RequestParam("id") Long id);
}
