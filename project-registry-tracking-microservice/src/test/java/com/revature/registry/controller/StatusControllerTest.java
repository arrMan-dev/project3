package com.revature.registry.controller;

import static org.hamcrest.CoreMatchers.isA;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.List;

import org.assertj.core.util.Lists;
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
import com.revature.registry.model.Status;
import com.revature.registry.service.StatusService;

@SpringBootTest(classes = ProjectRegistryTrackingApplication.class)
@ExtendWith(SpringExtension.class)
class StatusControllerTest {

	private MockMvc mockMvc;

	@Autowired
	@InjectMocks
	private StatusController statusController;

	@MockBean
	private StatusService statusService;
	
	 @BeforeEach
	    public void setUp() {
	        mockMvc = MockMvcBuilders.standaloneSetup(statusController).build();
	    }

	@Test
	void testGetAllStatuses() throws Exception {

		Status stat1 = new Status();
		stat1.setId(1);
		stat1.setName("NEEDS_ATTENTION");

		Status stat2 = new Status();
		stat2.setId(2);
		stat2.setName("ACTIVE");

		List<Status> statuses = Lists.newArrayList(stat1, stat2);
		when(statusService.getAllStatuses()).thenReturn(statuses);
		
		// mock request to controller
        mockMvc.perform(get("/api/status")).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", isA(List.class)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("NEEDS_ATTENTION"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("ACTIVE"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2));		
	}

	@Test
	void testGetStatusByIdWhenAvailableId() throws Exception {
		 // mock the return of getProjectById from ProjectService
        Status status = new Status();
        status.setId(2);
        status.setName("ACTIVE");        

        when(statusService.getStatusById((anyInt()))).thenReturn(status);

        // mock request to controller
        mockMvc.perform(get("/api/status/id/2")).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("ACTIVE"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2));                
	}
	
	@Test
	void testGetStatusByIdWhenUnavailableId() throws Exception {
		 // mock the return of getProjectById from ProjectService 
        when(statusService.getStatusById((anyInt()))).thenReturn(null);
        
        // mock request to controller
        mockMvc.perform(get("/api/status/id/3")).andExpect(status().isBadRequest());                                
	}
}
