package com.revature.registry.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.revature.registry.model.Project;
import com.revature.registry.model.Repository;

import lombok.Data;

@Data
public class OrganizationDTO {
    private int id;

    private String name;
    
    private Project project;
    
    private List<Repository> repositories = new ArrayList<>();

}
