package com.survey.mole.model.worktracker.employee;

import com.survey.mole.model.AbstractEntity;
import com.survey.mole.model.worktracker.Department;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name="work_period")
@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class WorkPeriod extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_period_id")
    private Long id;

    @Column(name = "start")
    private LocalDate start;

    @Column(name = "end_data")
    private LocalDate end;


    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "department_id")
    private Department department;
}
