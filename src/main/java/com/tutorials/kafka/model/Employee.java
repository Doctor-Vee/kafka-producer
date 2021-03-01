package com.tutorials.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {

    private String name;

    private String jobRole;

    private Double salary;

}
