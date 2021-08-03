package com.revature.registry.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.revature.registry.ProjectRegistryTrackingApplication;
import com.revature.registry.model.Phase;
import com.revature.registry.repository.PhaseRepository;

@SpringBootTest(classes = ProjectRegistryTrackingApplication.class)
@ExtendWith(SpringExtension.class)
 class PhaseServiceTest {
	
	private static Phase phase1, phase2;
	private static List<Phase> phaseList;
	
	@Autowired
    private static PhaseService phaseService;
	
	@Mock
	private static PhaseRepository phaseRepo;
	
	@BeforeEach
	public void setup() {
		
		phaseService = Mockito.mock(PhaseService.class);
		phaseList = new ArrayList<>();
		
		phase1 = new Phase(1, "test phase kind", "test phase description");
		phase2 = new Phase(2, "test phase kind2", "test phase description2");
		phaseList.add(phase1);
		phaseList.add(phase2);	
	}
	
	@Test
	 void getAllPhaseTest1() {
		Mockito.when(phaseService.getAllPhases()).thenReturn(phaseList);
		assertEquals(2, phaseService.getAllPhases().size());
	}
	
	@Test
	 void getPhaseByIdTest() {
		Mockito.when(phaseService.getPhaseById(1)).thenReturn(phase1);
		assertEquals(phase1, phaseService.getPhaseById(1));
	}
	
	@Test
	 void getPhaseByIdTest1() {
		Mockito.when(phaseService.getPhaseById(2)).thenReturn(phase2);
		assertEquals(phase2, phaseService.getPhaseById(2));
	}

}
