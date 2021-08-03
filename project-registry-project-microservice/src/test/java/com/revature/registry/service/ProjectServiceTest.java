package com.revature.registry.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.registry.ProjectMicroServiceApplication;
import com.revature.registry.model.Project;
import com.revature.registry.repository.ProjectRepository;

@SpringBootTest(classes = ProjectMicroServiceApplication.class)
class ProjectServiceTest {
    @MockBean
    ProjectRepository projectRepository;

    @Autowired
    ProjectService projectService;

    // Global variables
    static Project project1;
    static Project project2;

    @BeforeEach
    void setUp() {
        List<Project> projectList = new LinkedList<>();
        project1 = new Project();
        project2 = new Project();
        project1.setId(10);
        projectList.add(project1);

        // Mock Repo
        when(projectRepository.findAll()).thenReturn(projectList);
        when(projectRepository.findById(project1.getId())).thenReturn(Optional.ofNullable(project1));
        when(projectRepository.save(project2)).thenReturn(project2);
    }

    @Test
    void testGetProjectByIdReturnProject() {
        assertThat(projectService.getProjectById(10)).isEqualTo(project1);
    }

    @Test
    void testGetProjectByIdReturnNull() {
        assertThat(projectService.getProjectById(1)).isNull();
    }

    @Test
    void testGetAllProjectsListNotEmpty() {
        assertThat(projectService.getAllProjects()).isNotEmpty();
    }

    @Test
    void testCreateProjectReturnProject() {
        assertThat(projectService.createProject(project2)).isEqualTo(project2);
    }

    @Test
    void testDeleteProjectByIdReturnFalse() {
        assertThat(projectService.deleteProjectById(1)).isFalse();
    }

    @Test
    void testUpdateProjectByIdReturnProject() {
        project1.setDescription("Testing");
        assertThat(projectService.updateProjectById(project1.getId(), project1)).isEqualTo(project1);
    }

    @Test
    void testUpdateProjectByIdReturnNull() {
        Project testProject = new Project();
        testProject.setId(2);
        assertThat(projectService.updateProjectById(testProject.getId(), testProject)).isNull();
    }
}
