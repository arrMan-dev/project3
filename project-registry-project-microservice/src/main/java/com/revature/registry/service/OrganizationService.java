package com.revature.registry.service;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.registry.model.Organization;
import com.revature.registry.repository.OrganizationRepository;

@Service
public class OrganizationService {
    private static Logger log = Logger.getLogger(OrganizationService.class);

    private OrganizationRepository oRepo;

    public OrganizationService() {

    }

    @Autowired
    public OrganizationService(OrganizationRepository oRepo) {
        this.oRepo = oRepo;
    }

    public List<Organization> getAllOrganizations() {
        log.info("Fetching all organizations: "+ oRepo.findAll());
        return oRepo.findAll();

    }

    public Organization getOrganizationById(int id) {
        Optional<Organization> organization = oRepo.findById(id);

        if (organization.isPresent()) {
            log.info("Fetching Organization with id of " + id);
            return organization.get();
        }
        log.error("Unable to GET. Organization with id: " + id + " not found.");
        return null;

    }

    public Organization registerOrganization(Organization o) {
        Organization savedOrg;
        try {
            
            savedOrg = oRepo.save(o);
            log.debug("Organization created with the following properties: " + savedOrg.toString());
            return savedOrg;
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }

    }

    public Organization updateOrganizationById(int id, Organization newOrg) {
        Optional<Organization> organization = oRepo.findById(id);
        if (organization.isPresent()) {
            newOrg.setId(id);
            try {
                log.debug("Project with id: "+ id + " updated with the following properties: " + newOrg);
                return oRepo.save(newOrg);
            } catch (Exception e) {

                e.printStackTrace();
            }

        }
        log.error("Unable to update. Organization with id "+id+" not found.");
        return null;
    }

    // noContent() for ResponseEntity
    public boolean deleteOrganizationById(int id) {
        Optional<Organization> org = oRepo.findById(id);
        if (org.isPresent()) {
            oRepo.deleteById(id);
            log.debug("Organization with id:" +id+" deleted.");

            return true;
        }
        log.error("Unable to DELETE. Organization with id "+id+" not found.");
        return false;
    }

}
