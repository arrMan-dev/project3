package com.revature.registry.controller;

import java.util.List;

import org.apache.http.ParseException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.registry.model.Iteration;
import com.revature.registry.model.dto.IterationDTO;
import com.revature.registry.service.IterationService;

@RestController
@RequestMapping(value = "/api/iteration", produces = MediaType.APPLICATION_JSON_VALUE)
//@CrossOrigin(origins = "http://localhost:4200")
public class IterationController {
    @Autowired
    private IterationService iterationService;
    
    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("")
    public ResponseEntity<List<Iteration>> getAllIterations() {
        List<Iteration> list = iterationService.getAllIterations();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Iteration> getIterationById(@PathVariable("id") int id) {
        Iteration iteration = iterationService.getIterationById(id);
        if (iteration != null) {
            return ResponseEntity.ok(iteration);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("")
    public ResponseEntity<Iteration> createIteration(@RequestBody IterationDTO iterationDto) {
        Iteration iteration = convertToEntity(iterationDto);
        Iteration savedIteration = iterationService.createIteration(iteration);
        return new ResponseEntity<>(savedIteration, HttpStatus.OK);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<Iteration> updateIteration(@PathVariable("id") int id, @RequestBody IterationDTO iterationDto) {
        
        Iteration iteration = convertToEntity(iterationDto);
        Iteration iterationUpdated = iterationService.updateIterationById(id, iteration);
        if (iterationUpdated != null) {
            return new ResponseEntity<>(iterationUpdated, HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<Iteration> deleteIteration(@PathVariable("id") int id) {
        boolean result = iterationService.deleteIterationById(id);
        if (result) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    
    private Iteration convertToEntity(IterationDTO iterDto)throws ParseException{
        return modelMapper.map(iterDto, Iteration.class);
        
    }

}
