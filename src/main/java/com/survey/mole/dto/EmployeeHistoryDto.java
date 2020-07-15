package com.survey.mole.dto;

import com.survey.mole.model.worktracker.EmployeeDay;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class EmployeeHistoryDto extends AbstractDto{

    private Long id;

    private List<WorkPeriodDto> periods = new ArrayList<>();

    private List<EmployeeDay> attendances = new ArrayList<>();
}
