package com.survey.mole.dto;

import com.survey.mole.model.worktracker.employee.Address;
import com.survey.mole.model.worktracker.employee.Contact;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
public class EmployeeDto extends AbstractDto {

    private Long id;

    private String num;

    private String firstName;

    private String lastName;

    private LocalDate birthday;

    private String gender;

    private Boolean isRemote;

    private Address address;

    private Contact contact;

    private Long postId;

    private Long departmentId;

    private EmployeeHistoryDto employeeHistoryDto;
}
