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
import com.revature.registry.model.Organization;
import com.revature.registry.repository.OrganizationRepository;

@SpringBootTest(classes = ProjectMicroServiceApplication.class)
class OrganizationServiceTest {
    @MockBean
    OrganizationRepository organizationRepository;

    @Autowired
    OrganizationService organizationService;

    // Static Variables
    static Organization organization1;
    static Organization organization2;

    @BeforeEach
    void setUp() {
        List<Organization> organizationList = new LinkedList<>();
        organization1 = new Organization();
        organization2 = new Organization();
        organization1.setId(12);
        organizationList.add(organization1);

        // Mock Repo
        when(organizationRepository.findAll()).thenReturn(organizationList);
        when(organizationRepository.findById(organization1.getId())).thenReturn(Optional.ofNullable(organization1));
        when(organizationRepository.save(organization2)).thenReturn(organization2);
    }

    @Test
    void testGetAllOrganizationsReturnNotEmpty() {
        assertThat(organizationService.getAllOrganizations()).isNotEmpty();
    }

    @Test
    void testFindOrganizationByIdReturnOrganization() {
        assertThat(organizationService.getOrganizationById(organization1.getId())).isEqualTo(organization1);
    }

    @Test
    void testFindOrganizationByIdReturnNull() {
        assertThat(organizationService.getOrganizationById(1)).isNull();
        ;
    }

    @Test
    void testRegisterOrganizationReturnOrganization() {
        assertThat(organizationService.registerOrganization(organization2)).isEqualTo(organization2);
    }

    @Test
    void testUpdateOrganizationByIdReturnNull() {
        Organization testOrg = new Organization();
        testOrg.setId(2);
        assertThat(organizationService.updateOrganizationById(testOrg.getId(), testOrg)).isNull();
    }

//	@Test
//	void testUpdateOrganizationByIdReturnOrganization() {
//		organization1.setName("Test Org.");
//		assertThat(organizationService
//				.updateOrganizationById(organization1.getId(), organization1))
//				.isEqualTo(organization1);
//	}

    @Test
    void testDeleteOrganizationByIdReturnFalse() {
        assertThat(organizationService.deleteOrganizationById(1)).isFalse();
    }

}
