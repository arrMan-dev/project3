package com.revature.registry.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.revature.registry.ProjectRegistryTrackingApplication;
import com.revature.registry.model.Status;
import com.revature.registry.repository.StatusRepository;

@SpringBootTest(classes = ProjectRegistryTrackingApplication.class)
@ExtendWith(SpringExtension.class)
class StatusServiceTest {

	@Autowired
	@InjectMocks
	private StatusService statusService;

	@MockBean
	private StatusRepository statusRepository;

	@Test
	void testGetAllStatuses() {
		Status s1 = new Status();
		s1.setId(1);
		s1.setName("ACTIVE");

		Status s2 = new Status();
		s2.setId(2);
		s2.setName("NEEDS_ATTENTION");

		List<Status> statuses = Lists.newArrayList(s1, s2);
		when(statusRepository.findAll()).thenReturn(statuses);

		// check to see if the method returns the correct data
		assertThat(statusService.getAllStatuses()).isEqualTo(statuses);
	}

	@Test
	void testGetStatusByIdWhenStatusPresent() {
		Status s = new Status();
		s.setId(1);
		s.setName("ACTIVE");

		when(statusRepository.findById((anyInt()))).thenReturn(Optional.of(s));
		
		// check to see if the method returns the correct data
		assertThat(statusService.getStatusById(1)).isEqualTo(s);
	}
	
	@Test
	void testGetStatusByIdWhenStatusNotPresent() {
		
		// no need to mock anything since iterationRepository.findById() will return an
        // empty optional
		when(statusRepository.findById((anyInt()))).thenReturn(Optional.empty());
		
		// check to see if the method returns the correct data
		assertThat(statusService.getStatusById(1)).isNull();;
	}
}
