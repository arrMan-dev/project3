package com.revature.registry.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * A {@link Phase} refers to a particular step in the Center of Excellence'
 * process for Project Management.
 *
 * Currently (as of Q1 2021), the valid phases are as follows:
 * <ol>
 * <li>BACKLOG_GENERATED</li>
 * <li>TRAINER_APPROVED</li>
 * <li>HANDOFF_SCHEDULED</li>
 * <li>RESOURCE_ALLOCATION</li>
 * <li>CHECKPOINT_MEETING</li>
 * <li>CODE_REVIEW</li>
 * <li>COMPLETE</li>
 * </ol>
 */

@Entity
@Data
public class Phase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String kind;

    private String description;
}
