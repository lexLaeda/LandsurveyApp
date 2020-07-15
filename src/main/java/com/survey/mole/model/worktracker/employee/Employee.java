package com.survey.mole.model.worktracker.employee;

import com.survey.mole.model.AbstractEntity;
import com.survey.mole.model.worktracker.Department;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employee")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Employee extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    @Column(name = "num")
    @EqualsAndHashCode.Include
    private String num;

    @Column(name = "first_name")
    @EqualsAndHashCode.Include
    private String firstName;

    @Column(name = "last_name")
    @EqualsAndHashCode.Include
    private String lastName;

    @Column(name = "birthday")
    @EqualsAndHashCode.Include
    private LocalDate birthday;

    @Column(name = "gender")
    @EqualsAndHashCode.Include
    private String gender;

    @Column(name = "is_remote")
    private Boolean isRemote;

    @JoinColumn(name = "address_id")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;

    @JoinColumn(name = "contact_id")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Contact contact;

    @JoinColumn(name = "post_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private Post post;

    @JoinColumn(name = "department_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private Department department;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "employee_history")
    @EqualsAndHashCode.Exclude
    private EmployeeHistory employeeHistory;
}
