package com.revature.registry.controller;

import static org.hamcrest.CoreMatchers.isA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.registry.ProjectMicroServiceApplication;
import com.revature.registry.model.Project;
import com.revature.registry.model.dto.ProjectDTO;
import com.revature.registry.service.ProjectService;

@SpringBootTest(classes = ProjectMicroServiceApplication.class)
public class ProjectControllerTest {

	private MockMvc mockMvc;

	private ModelMapper modelMapper = new ModelMapper();

	@MockBean
	ProjectService projectService;

	@Autowired
	@InjectMocks
	private ProjectController projectController;

	// static variables
	Project p1;
	Project p2;
	List<Project> projectList;

	@BeforeEach
	void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();

		p1 = new Project();
		p1.setId(101);

		p2 = new Project();
		p2.setId(102);

		projectList = new LinkedList<>();
		projectList.add(p1);
		projectList.add(p2);
	}

	@Test
	public void testGetAllProjects() throws Exception {
		when(projectService.getAllProjects()).thenReturn(projectList);

		mockMvc.perform(get("/api/project")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.*", isA(List.class)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(p1.getId()))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(p2.getId()));
	}

	@Test
	public void testGetProjectById() throws Exception {
		when(projectService.getProjectById(101)).thenReturn(p1);

		mockMvc.perform(get("/api/project/id/101")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(p1.getId()));
	}

	@Test
	public void testCreateProject() throws Exception {
		Project pCreate = new Project();
		pCreate.setId(103);

		when(projectService.createProject(pCreate)).thenReturn(pCreate);

		mockMvc.perform(post("/api/project/").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(pCreate))).andExpect(status().isCreated());
	}

	@Test
	public void testUpdateProject() throws Exception {
		Project pUpdate = new Project();
		when(projectService.updateProjectById(p1.getId(), pUpdate)).thenReturn(pUpdate);

		mockMvc.perform(put("/api/project/id/" + p1.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(pUpdate))).andExpect(status().isOk());
	}

	@Test
	void convertToEntityTest() {
		ProjectDTO projectDto = new ProjectDTO();
		projectDto.setName("test");

		Project project = modelMapper.map(projectDto, Project.class);
		assertEquals(projectDto.getId(), project.getId());
		assertEquals(projectDto.getName(), project.getName());

	}

	@Test
	@Disabled
	public void testDeleteProject() throws Exception {
		Project pDelete = new Project();
		pDelete.setId(104);

		doNothing().when(projectService).deleteProjectById(104);

		mockMvc.perform(delete("/api/project/id/" + pDelete.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
