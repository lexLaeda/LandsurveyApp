package com.survey.mole.model.worktracker;

import com.survey.mole.model.worktracker.employee.Employee;

import java.util.ArrayList;
import java.util.List;

public class Department {

    private Long id;
    private String name;
    private List<Employee> employees = new ArrayList<>();
}
