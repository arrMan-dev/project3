package com.revature.registry.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * A {@link Status} is pertains to the current status of a {@link Project}.
 *
 * Currently (as of Q1 2021), the valid statuses are
 *  <ol>
 *     <li>ACTIVE</li>
 *     <li>NEEDS_ATTENTION</li>
 *     <li>ARCHIVED</li>
 *  </ol>
 */

@Entity
@Data
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

}
