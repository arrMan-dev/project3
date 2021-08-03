package com.revature.registry.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.registry.model.Iteration;

public interface IterationRepository extends JpaRepository<Iteration, Integer> {

}
