package com.revature.registry.controller;

import static org.hamcrest.CoreMatchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.revature.registry.ProjectRegistryTrackingApplication;
import com.revature.registry.model.Phase;
import com.revature.registry.service.PhaseService;

@SpringBootTest(classes = ProjectRegistryTrackingApplication.class)
@ExtendWith(SpringExtension.class)
 class PhaseControllerTest {

	private static Phase phase1, phase2;
	private static List<Phase> phaseList;

	private MockMvc mockMvc;

	@Autowired
	@InjectMocks
	private PhaseController phaseController;

	@MockBean
	private PhaseService phaseService;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(phaseController).build();

		phaseList = new ArrayList<>();

		phase1 = new Phase(1, "test phase kind", "test phase description");
		phase2 = new Phase(2, "test phase kind2", "test phase description2");
		phaseList.add(phase1);
		phaseList.add(phase2);
	}

	@Test
	 void getAllPhaseTest() throws Exception {

		when(phaseService.getAllPhases()).thenReturn(phaseList);

		// mock request to controller
		mockMvc.perform(get("/api/phase")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.*", isA(List.class)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].kind").value("test phase kind"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].kind").value("test phase kind2"));

	}

	@Test
	 void getPhaseByIdTest() throws Exception {
		when(phaseService.getPhaseById(1)).thenReturn(phase1);

		mockMvc.perform(get("/api/phase/id/1")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.*", isA(List.class)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.kind").value("test phase kind"));
	}
	
	@Test
	 void getPhaseByIdTest2() throws Exception {
		when(phaseService.getPhaseById(2)).thenReturn(phase2);

		mockMvc.perform(get("/api/phase/id/2")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.*", isA(List.class)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$.kind").value("test phase kind2"));
	}

}
