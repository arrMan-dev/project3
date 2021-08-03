package com.revature.registry.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.registry.model.Phase;
import com.revature.registry.repository.PhaseRepository;

@Service
public class PhaseService {
    private static Logger log = Logger.getLogger(PhaseService.class);

	
	@Autowired
	private PhaseRepository phaseRepository;

	public List<Phase> getAllPhases() {
	    log.info("Fetching all phases: "+ phaseRepository.findAll());
	    return phaseRepository.findAll();
		
	}

	public Phase  getPhaseById(int id) {
		Phase phase = phaseRepository.findById(id);
		if (phase != null) {
		    log.info("Fetching Phase with id: "+ id);
			return phase;
		}
		log.error("Unable to GET. Phase with id: "+ id+" not found.");
		return null;
	}
}
