package com.survey.mole.model.worktracker;


import com.survey.mole.model.AbstractEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "employee_day")
@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class EmployeeDay extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_day_id")
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "code_id")
    private Code code;
}
