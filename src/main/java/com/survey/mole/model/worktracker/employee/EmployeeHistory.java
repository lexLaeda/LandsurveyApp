package com.survey.mole.model.worktracker.employee;

import com.survey.mole.model.AbstractEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "employee_history")
@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class EmployeeHistory extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_history")
    private Long id;
    private List<WorkPeriod> durations = new ArrayList<>();
}
