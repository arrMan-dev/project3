package com.revature.registry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.registry.model.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer>{
 
}
