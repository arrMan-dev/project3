package com.revature.registry.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *  An {@link Account} is the user account associated with Project Registry.
 *
 *  An {@link Account} is simply a container of a username and a {@link Role}, and is used to correspond with either a
 *  {@link Project}, if the {@link Account} has a {@link Role} of type `ROLE_PRODUCT_OWNER`, or is corresponded with an
 *  {@link Iteration}, if the {@link Account} has a {@link Role} of type `ROLE_TRAINER`.
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    @JsonIgnore
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public Account(int id) {
        this.id = id;
    }
}
