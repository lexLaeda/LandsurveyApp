package com.survey.mole.dto;

import com.survey.mole.model.worktracker.employee.Address;
import com.survey.mole.model.worktracker.employee.Contact;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeDto extends AbstractDto {

    private Long id;

    private String num;

    private String firstName;

    private String lastName;

    private LocalDate birthday;

    private Character gender;

    private Boolean isRemote;

    private Address address;

    private Contact contact;

    private Long postId;

    private Long departmentId;
}
