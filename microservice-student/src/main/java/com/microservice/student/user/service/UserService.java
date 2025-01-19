package com.microservice.student.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.student.user.model.User;
import com.microservice.student.user.repository.IUserRepository;

@Service
public class UserService {
  @Autowired
  private IUserRepository repository;

  // List all users
  public List<User> findAll() {
    return repository.findAll();
  }

  // Show user by id
  public User findById(Long id) {
    var userOp = repository.findById(id);
    if (userOp.isEmpty()) {
      return userOp.get();
    }
    return null;
  }

  // show user by email
  public User findByEmail(String email) {
    var userOp = repository.findByEmail(email);
    if (userOp.isEmpty()) {
      return userOp.get();
    }
    return null;
  }

  // Delete by id
  public void deleteById(Long id) {
    if (findById(id) != null) {
      repository.deleteById(id);
    }
  }

  // update user
  public void update(User user) {
    if (user.getId() != null) {
      repository.update(user);
    }
  }
}
