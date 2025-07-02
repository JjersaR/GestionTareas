package com.microservice.user.user.controller;

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

import com.microservice.user.user.entity.User;
import com.microservice.user.user.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {

  @Autowired
  private UserService service;

  @GetMapping
  public ResponseEntity<?> findAll() {
    var users = service.findAll();
    return (users.isEmpty()) ? ResponseEntity.noContent().build() : ResponseEntity.ok(users);
  }

  @GetMapping("/by")
  public ResponseEntity<?> getUser(
      @RequestParam(required = false) Long id,
      @RequestParam(required = false) String email) {

    if (id != null) {
      var user = service.findById(id);
      return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    } else if (email != null) {
      var user = service.findByEmail(email);
      return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.badRequest().body("Debe proporcionar 'id' o 'email' como parámetro");
    }
  }

  @PostMapping
  public ResponseEntity<?> saveUser(@RequestBody User user) throws URISyntaxException {
    service.save(user);
    return ResponseEntity.created(new URI("api/user")).build();
  }

  @PutMapping
  public ResponseEntity<?> updateUser(@RequestParam Long id, @RequestBody User user) {
    user.setId(id);
    service.update(user);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping
  public ResponseEntity<?> deleteUser(@RequestParam Long id) {
    service.deleteById(id);
    return ResponseEntity.ok().build();
  }

}
