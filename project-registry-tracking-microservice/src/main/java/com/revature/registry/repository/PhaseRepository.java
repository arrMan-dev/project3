package com.revature.registry.repository;

import com.revature.registry.model.Phase;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PhaseRepository extends JpaRepository<Phase, Integer>{
	
	Phase findById(int id);

}
