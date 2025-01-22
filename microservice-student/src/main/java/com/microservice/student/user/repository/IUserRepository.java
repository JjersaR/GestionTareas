package com.microservice.student.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.microservice.student.user.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

  // Find user by ID
  Optional<User> findById(Long id);

  // Find user by email
  Optional<User> findByEmail(String email);

  Optional<User> findByName(String name);

  // update user
  @Query(value = """
      UPDATE User u SET u.name = COALESCE(:#{#user.name}, u.name), u.password = COALESCE(:#{#user.password}, u.password), u.email = COALESCE(:#{#user.email}, u.email)
      WHERE u.id = :#{#user.id}
      """, nativeQuery = true)
  void update(User user);
}
