package com.revature.registry.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * An {@link Iteration} is a batch's iteration with a {@link Project}, bound by the startDate and endDate.
 *
 * No two {@link Iteration}'s on a {@link Project} should have intersecting startDate/endDate, as this is a violation
 * of business rules.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Iteration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate startDate;

    private LocalDate endDate;

    private String batchId;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
