package com.survey.mole.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
public class WorkPeriodDto extends AbstractDto{

    private Long id;

    private LocalDate start;

    private LocalDate end;

    private Long departmentId;
}