package com.revature.registry.service;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.registry.model.Iteration;
import com.revature.registry.repository.IterationRepository;

@Service
public class IterationService {
    private static Logger log = Logger.getLogger(IterationService.class);

    @Autowired
    private IterationRepository iterationRepository;

    public List<Iteration> getAllIterations() {
        log.info("Fetching all iterations: "+ iterationRepository.findAll());
        return iterationRepository.findAll();
    }

    public Iteration getIterationById(int id) {
        Optional<Iteration> iteration = iterationRepository.findById(id);
        if (iteration.isPresent()) {
            log.info("Fetching Iteration with id: "+ id);
            return iteration.get();
        }
        log.error("Unable to GET. Iteration with id " + id+" not found.");
        return null;
    }

    public Iteration createIteration(Iteration iteration) {
        log.debug("Iteration created with the following properties: "+ iteration.toString());
        return iterationRepository.save(iteration);
    }

    public Iteration updateIterationById(int id, Iteration newIteration) {
        Optional<Iteration> iteration = iterationRepository.findById(id);
        if (iteration.isPresent()) {
            newIteration.setId(id);
            Iteration saved = iterationRepository.save(newIteration);
            log.debug("Iteration with id: " + id + " Updated with the following properties: " + newIteration);
            return saved;
        }

        log.error("Unable to update. Iteration with id " + id + " not found.");
        return null;
    }

    public boolean deleteIterationById(int id) {
        Optional<Iteration> iteration = iterationRepository.findById(id);
        if (iteration.isPresent()) {
            iterationRepository.deleteById(id);
            log.debug("Iteration Deleted with id: " + id);
            return true;
        }
        log.error("Unable to DELETE. Iteration with id:" + id + " not found.");
        return false;
    }

}
