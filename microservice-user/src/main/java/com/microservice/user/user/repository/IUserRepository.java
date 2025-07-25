package com.microservice.user.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.microservice.user.user.entity.User;

import jakarta.transaction.Transactional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

  // Find user by ID
  Optional<User> findById(Long id);

  // Find user by email
  Optional<User> findByEmail(String email);

  Optional<User> findByName(String name);

  // update user
  @Transactional
  @Modifying
  @Query("""
      UPDATE User u SET u.name = COALESCE(:#{#user.name}, u.name), u.email = COALESCE(:#{#user.email}, u.email)
      WHERE u.id = :#{#user.id}
      """)
  void update(User user);
}
