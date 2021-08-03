package com.revature.registry.controller;

import static org.hamcrest.CoreMatchers.isA;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.registry.ProjectMicroServiceApplication;
import com.revature.registry.model.Organization;
import com.revature.registry.model.dto.OrganizationDTO;
import com.revature.registry.service.OrganizationService;

@SpringBootTest(classes = ProjectMicroServiceApplication.class)
@ExtendWith(SpringExtension.class)
class OrganizationControllerTest {

    private MockMvc mockMvc;

    private ModelMapper modelMapper = new ModelMapper();

    @MockBean
    private OrganizationService organizationService;

    @Autowired
    @InjectMocks
    private OrganizationController organizationController;

    // Static variables
    Organization org1;
    Organization org2;
    List<Organization> orgList;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(organizationController).build();

        org1 = new Organization();
        org1.setId(10);

        org2 = new Organization();
        org2.setId(11);

        orgList = new LinkedList<>();
        orgList.add(org1);
        orgList.add(org2);
    }

    @Test
    void testGetAllOrganizations() throws Exception {
        when(organizationService.getAllOrganizations()).thenReturn(orgList);

        mockMvc.perform(get("/api/organization")).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", isA(List.class)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(org1.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(org2.getId()));
    }

    @Test
    void testGetOrganizationById() throws Exception {
        when(organizationService.getOrganizationById(org1.getId())).thenReturn(org1);

        mockMvc.perform(get("/api/organization/id/" + org1.getId())).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(org1.getId()));
    }

    @Test
    void testRegisterOrganization() throws Exception {
        OrganizationDTO orgDto = new OrganizationDTO();
        orgDto.setId(100);
        Organization org = modelMapper.map(orgDto, Organization.class);
        
        when(organizationService.registerOrganization(org)).thenReturn(org);
       
      
        mockMvc.perform(post("/api/organization/").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(org))).andExpect(status().isCreated());

    }

    @Test
    void testUpdateOrganizationById() throws Exception {
        Organization newOrg = new Organization();
        when(organizationService.updateOrganizationById(org1.getId(), newOrg)).thenReturn(newOrg);

        mockMvc.perform(put("/api/organization/id/" + org1.getId()).contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(newOrg))).andExpect(status().isOk());
    }

    @Test
    void testDeleteOrganization() throws Exception {

        when(organizationService.deleteOrganizationById(anyInt())).thenReturn(true);

        mockMvc.perform(delete("/api/organization/id/1")).andExpect(status().isNoContent());

    }

    @Test
    void convertToEntityTest() {
        OrganizationDTO orgDto = new OrganizationDTO();
        orgDto.setName("test");
        
        Organization org = modelMapper.map(orgDto,Organization.class);
        assertEquals(orgDto.getId(),org.getId());
        assertEquals(orgDto.getName(),org.getName());

    }
}
