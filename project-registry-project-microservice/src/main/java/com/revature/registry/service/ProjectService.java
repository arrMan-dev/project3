package com.revature.registry.service;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import com.revature.registry.model.Project;
import com.revature.registry.repository.ProjectRepository;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProjectService {
    private static Logger log = Logger.getLogger(ProjectService.class);
    
    
    private ProjectRepository pRepo;
    
    public ProjectService() {
        
    }
    
    @Autowired
    public ProjectService(ProjectRepository pRepo) {
        this.pRepo = pRepo;
    }
    

    public List<Project> getAllProjects() {
 //       log.setLevel(Level.DEBUG); if Logger is not working, use setLevel
        log.debug("Fetching all products: " + pRepo.findAll());
        return pRepo.findAll();
    }

    public Project getProjectById(int id) {
        Optional<Project> project = pRepo.findById(id);
        if (project.isPresent()) {
            log.debug("Fetching Project with id of: " + project.get());
            return project.get();
        }
        log.error("Unable to GET. Project with id " + id + " not found.");
        return null;
    }

    public Project createProject(Project project) {
        Project savedProject = pRepo.save(project);
        log.debug("Project created with the following properties: " + savedProject.toString());
       
        return savedProject;
    }

    public Project updateProjectById(int id, Project newProject) {
        Optional<Project> project = pRepo.findById(id);
        if (project.isPresent()) {
            newProject.setId(id);
            pRepo.save(newProject);
            log.debug("Project with id: " + id + " Updated with the following properties: " + newProject);
            return project.get();
        }

        log.error("Unable to update. Project with id " + id + " not found.");
        return null;
    }

    public boolean deleteProjectById(int id) {
        Optional<Project> project = pRepo.findById(id);
        if (project.isPresent()) {
            pRepo.deleteById(id);
            log.debug("Project Deleted with id: " + id);
            return true;
        }
        log.error("Unable to DELETE. Project with id " + id+ " not found");
        return false;
    }

}
