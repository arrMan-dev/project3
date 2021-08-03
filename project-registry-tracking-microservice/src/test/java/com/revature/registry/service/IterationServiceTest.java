package com.revature.registry.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.revature.registry.ProjectRegistryTrackingApplication;
import com.revature.registry.model.Iteration;
import com.revature.registry.repository.IterationRepository;
import com.revature.registry.service.IterationService;

@SpringBootTest(classes = ProjectRegistryTrackingApplication.class)
@ExtendWith(SpringExtension.class)
class IterationServiceTest {

    @Autowired
    private IterationService iterationService;

    @MockBean
    private IterationRepository iterationRepository;

    @Test
    void when_getIterationByID_return_correct_list() {
        // mock the return of getAllIterations from IterationRepository

        Iteration iteration1 = new Iteration();
        iteration1.setId(12);
        iteration1.setStartDate(LocalDate.now().minusDays(1));
        iteration1.setEndDate(LocalDate.now());
        iteration1.setBatchId("107235");

        Iteration iteration2 = new Iteration();
        iteration2.setId(12);
        iteration2.setStartDate(LocalDate.now().minusDays(1));
        iteration2.setEndDate(LocalDate.now());
        iteration2.setBatchId("107239");

        List<Iteration> iterations = Lists.newArrayList(iteration1, iteration2);

        when(iterationRepository.findAll()).thenReturn(iterations);

        // check to see if the method returns the correct data
        List<Iteration> output = iterationService.getAllIterations();
        assertThat(output).isEqualTo(iterations);
    }

    @Test
    void when_getIterationByID_then_return_correct_iteration() {
        // mock the return of getIterationById from IterationRepository

        Iteration iteration = new Iteration();
        iteration.setId(12);
        iteration.setStartDate(LocalDate.now().minusDays(1));
        iteration.setEndDate(LocalDate.now());
        iteration.setBatchId("107235");

        when(iterationRepository.findById(12)).thenReturn(Optional.of(iteration));

        // check to see if the method returns the correct data
        Iteration output = iterationService.getIterationById(12);
        assertThat(output).isEqualTo(iteration);
    }

    @Test
    void when_getIterationByID_bad_id_then_return_bad_request() {
        // no need to mock anything since iterationRepository.findById() will return an
        // empty optional
        when(iterationRepository.findById(anyInt())).thenReturn(Optional.empty());

        // check to see if the method returns the correct data
        Iteration output = iterationService.getIterationById(13);
        assertThat(output).isNull();
    }

    @Test
    void when_createIteration_return_created_iteration() {
        // mock the return of createIteration from IterationRepository

        Iteration iteration = new Iteration();
        iteration.setId(12);
        LocalDate localDateNow = LocalDate.now();
        iteration.setStartDate(localDateNow.minusDays(1));
        iteration.setEndDate(localDateNow);
        iteration.setBatchId("107235");

        when(iterationRepository.save(any(Iteration.class))).thenReturn(iteration);

        // check to see if the method returns the correct data
        Iteration input = new Iteration();
        input.setStartDate(localDateNow.minusDays(1));
        input.setEndDate(localDateNow);
        Iteration output = iterationService.createIteration(input);
        assertThat(output).isEqualTo(iteration);
    }

    @Test
    void when_updateIterationByID_then_return_updated_iteration() {
        // mock the return of updateIterationById from IterationRepository

        Iteration iteration = new Iteration();
        iteration.setId(12);
        iteration.setStartDate(LocalDate.now().minusDays(1));
        iteration.setEndDate(LocalDate.now());
        iteration.setBatchId("107235");

        // mock check to see if object with id exists in db
        when(iterationRepository.findById(anyInt())).thenReturn(Optional.of(iteration));
        // mock the update
        when(iterationRepository.save(any(Iteration.class))).thenReturn(iteration);

        // check to see if the method returns the correct data
        Iteration input = new Iteration();
        input.setStartDate(LocalDate.now().minusDays(1));
        input.setEndDate(LocalDate.now());
        Iteration output = iterationService.updateIterationById(12, input);
        assertThat(output).isEqualTo(iteration);
    }

    @Test
    void when_updateIterationByID_and_id_invalid_then_return_updated_iteration() {
        // mock the return of updateIterationById from IterationRepository

        Iteration iteration = new Iteration();
        iteration.setId(12);
        iteration.setStartDate(LocalDate.now().minusDays(1));
        iteration.setEndDate(LocalDate.now());
        iteration.setBatchId("107235");

        // mock check to see that object with id does not exist in db
        when(iterationRepository.findById(anyInt())).thenReturn(Optional.empty());
        // mock the update
        when(iterationRepository.save(any(Iteration.class))).thenReturn(iteration);

        // check to see if the method returns the correct data
        Iteration input = new Iteration();
        input.setStartDate(LocalDate.now().minusDays(1));
        input.setEndDate(LocalDate.now());
        Iteration output = iterationService.updateIterationById(12, input);
        assertThat(output).isNull();
    }

    @Test
    void when_deleteIterationById_then_return_no_content() {
        // mock the return of deleteIterationById from IterationRepository

        Iteration iteration = new Iteration();
        iteration.setId(12);
        iteration.setStartDate(LocalDate.now().minusDays(1));
        iteration.setEndDate(LocalDate.now());
        iteration.setBatchId("107235");

        ResponseEntity<Iteration> expected = new ResponseEntity<Iteration>(HttpStatus.NO_CONTENT);
        // mock check to see if object with id exists in db
        when(iterationRepository.findById(anyInt())).thenReturn(Optional.of(iteration));
        // you don't have to mock the delete since it has a void return type

        // check to see if the method returns the correct data
        boolean output = iterationService.deleteIterationById(12);
        assertThat(output).isTrue();
    }

    @Test
    void when_deleteIterationByID_and_id_invalid_then_return_bad_request() {
        // mock the return of deleteIterationById from IterationRepository

        Iteration iteration = new Iteration();
        iteration.setId(12);
        iteration.setStartDate(LocalDate.now().minusDays(1));
        iteration.setEndDate(LocalDate.now());
        iteration.setBatchId("107235");

        // mock check to see if object with id exists in db
        when(iterationRepository.findById(anyInt())).thenReturn(Optional.empty());
        // you don't have to mock the delete since it has a void return type

        // check to see if the method returns the correct data
        boolean output = iterationService.deleteIterationById(12);
        assertFalse(output);
    }

}
