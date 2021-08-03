package com.revature.registry.model.dto;

import java.time.LocalDate;

import com.revature.registry.model.Project;

import lombok.Data;

@Data
public class IterationDTO {
    private int id;

    private String batchId;

    private Project project;

    private LocalDate startDate;

    private LocalDate endDate;

}
