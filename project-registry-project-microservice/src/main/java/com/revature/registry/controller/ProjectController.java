package com.revature.registry.controller;

import java.net.URI;
import java.util.List;

import com.revature.registry.model.Project;

import com.revature.registry.model.dto.ProjectDTO;
import com.revature.registry.service.ProjectService;

import org.apache.http.ParseException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/project", produces = MediaType.APPLICATION_JSON_VALUE)
//@CrossOrigin(origins = "*")
public class ProjectController {

    private ProjectService pServ;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public ProjectController(ProjectService pServ) {
        this.pServ = pServ;
    }

    @GetMapping("")
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> pList = pServ.getAllProjects();

        return new ResponseEntity<>(pList, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("id") int id) {
        Project p = pServ.getProjectById(id);

        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Project> createProject(@RequestBody ProjectDTO projectDto) {
        Project project = convertToEntity(projectDto);
        Project newP = pServ.createProject(project);
        String location = String.format("/api/project/%s", newP.getId());
        return ResponseEntity.created(URI.create(location)).body(newP);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable("id") int id, @RequestBody ProjectDTO projectDto) {
        Project project = convertToEntity(projectDto);
        Project updateP = pServ.updateProjectById(id, project);

        return new ResponseEntity<>(updateP, HttpStatus.OK);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<Project> deleteUser(@PathVariable("id") int id) {
        if (pServ.deleteProjectById(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    private Project convertToEntity(ProjectDTO projectDto) throws ParseException {
        return modelMapper.map(projectDto, Project.class);

    }
}
