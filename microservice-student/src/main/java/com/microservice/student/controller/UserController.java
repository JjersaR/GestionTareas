package com.microservice.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.student.user.model.User;
import com.microservice.student.user.service.UserService;

@RestController
@RequestMapping("api/user/")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping("info")
  public ResponseEntity<User> getUser(@RequestParam Long id) {
    return ResponseEntity.ok(userService.findById(id));
  }
}
