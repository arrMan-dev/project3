package com.revature.registry.controller;

import java.net.URI;
import java.util.List;

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

import com.revature.registry.model.Organization;
import com.revature.registry.model.dto.OrganizationDTO;
import com.revature.registry.service.OrganizationService;

@RestController
@RequestMapping(value = "/api/organization", produces = MediaType.APPLICATION_JSON_VALUE)
//@CrossOrigin(origins = "*")
public class OrganizationController {

    private OrganizationService oServ;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public OrganizationController(OrganizationService oServ) {
        this.oServ = oServ;
    }

    @GetMapping("")
    public ResponseEntity<List<Organization>> getAllOrganizations() {

        List<Organization> oList = oServ.getAllOrganizations();

        return new ResponseEntity<>(oList, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Organization> getOrganizationById(@PathVariable("id") int id) {
        Organization org = oServ.getOrganizationById(id);
        return new ResponseEntity<>(org, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Organization> registerOrganization(@RequestBody OrganizationDTO orgDto) {
        Organization o = convertToEntity(orgDto);

        Organization savedOrg = oServ.registerOrganization(o);
        String location = String.format("/api/organization/%s", savedOrg.getId());
        return ResponseEntity.created(URI.create(location)).body(savedOrg);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Organization> updateOrganizationById(@PathVariable("id") int id,
            @RequestBody OrganizationDTO orgDto) {
        
        Organization newOrg = convertToEntity(orgDto);
        Organization updateOrg = oServ.updateOrganizationById(id, newOrg);

        return new ResponseEntity<>(updateOrg, HttpStatus.OK);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<Organization> deleteOrganizationById(@PathVariable int id) {
        if (oServ.deleteOrganizationById(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    private Organization convertToEntity(OrganizationDTO orgDto) throws ParseException {
        return modelMapper.map(orgDto, Organization.class);

    }

}
