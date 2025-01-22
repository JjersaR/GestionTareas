package com.microservice.student.permits.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.student.permits.model.Permit;

@Repository
public interface IPermitRepository extends JpaRepository<Permit, Long> {

}
