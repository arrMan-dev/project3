package com.revature.registry.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A {@link Role} is used to signify the level of access that is associated with an {@link Account}.
 *
 * A {@link Role} with type of `ROLE_PRODUCT_OWNER` is able to modify any {@link Project}
 * A {@link Role} with type of `ROLE_TRAINER` is able to view any {@link Project} in which they have participated in an
 *          {@link Iteration} on.
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;

}
