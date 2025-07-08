package com.microservice.user.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.user.user.dto.UserDTO;
import com.microservice.user.user.entity.User;
import com.microservice.user.user.repository.IUserRepository;

@Service
public class UserService {

  @Autowired
  private IUserRepository repository;

  // Método auxiliar para convertir User -> UserDTO
  private UserDTO convertToDto(User user) {
    return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getCreatedAt());
  }

  // List all users
  public List<UserDTO> findAll() {
    var users = repository.findAll();
    return users.stream().map(this::convertToDto).collect(Collectors.toList());
  }

  // Show user by id
  public UserDTO findById(Long id) {
    var userOp = repository.findById(id);
    if (!userOp.isEmpty()) {
      var user = userOp.get();

      return convertToDto(user);
    }
    return null;
  }

  // show user by email
  public UserDTO findByEmail(String email) {
    var userOp = repository.findByEmail(email);
    if (!userOp.isEmpty()) {
      var user = userOp.get();

      return convertToDto(user);
    }
    return null;
  }

  // Delete by id
  public void deleteById(Long id) {
    if (findById(id) != null) {
      repository.deleteById(id);
    }
  }

  // save user
  public void save(User user) {
    repository.save(user);
  }

  // update user
  public void update(User user) {
    if (user.getId() != null) {
      repository.update(user);
    }
  }
}
