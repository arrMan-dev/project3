package com.revature.registry.service;

import java.util.List;
import java.util.Optional;

import com.revature.registry.model.Status;
import com.revature.registry.repository.StatusRepository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class StatusService {
	private static Logger log = Logger.getLogger(StatusService.class);

	@Autowired
	private StatusRepository statusRepository;

	public List<Status> getAllStatuses() {

		log.debug("Getting all statuses");
		return statusRepository.findAll();
	}

	public Status getStatusById(int id) {
		log.debug("Getting status by Id");
		Optional<Status> status = statusRepository.findById(id);
		if (status.isPresent()) {
			log.debug("Fetching Status with id of " + id);
			return status.get();
		} else {
			log.error("Unable to GET. status with id " + id + " not found");
			return null;
		}
	}
}
