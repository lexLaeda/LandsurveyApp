package com.survey.mole.mapper;

import com.survey.mole.dto.EmployeeHistoryDto;
import com.survey.mole.dto.WorkPeriodDto;
import com.survey.mole.model.worktracker.employee.EmployeeHistory;
import com.survey.mole.model.worktracker.employee.WorkPeriod;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeHistoryMapper  extends AbstractMapper<EmployeeHistory, EmployeeHistoryDto>{

    private ModelMapper modelMapper;

    private WorkPeriodMapper workPeriodMapper;

    @Autowired
    public EmployeeHistoryMapper(ModelMapper modelMapper, WorkPeriodMapper workPeriodMapper) {
        super(modelMapper, EmployeeHistory.class, EmployeeHistoryDto.class);
        this.modelMapper = modelMapper;
        this.workPeriodMapper = workPeriodMapper;
    }

    @PostConstruct
    public void initMapper(){
        modelMapper.createTypeMap(EmployeeHistory.class,EmployeeHistoryDto.class).setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(EmployeeHistoryDto.class,EmployeeHistory.class).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(EmployeeHistory source, EmployeeHistoryDto destination) {
        List<WorkPeriod> periods = source.getPeriods();
        List<WorkPeriodDto> collect = periods.stream()
                .map(workPeriod -> workPeriodMapper.toDto(workPeriod))
                .collect(Collectors.toList());
        destination.setPeriods(collect);
        super.mapSpecificFields(source, destination);
    }

    @Override
    protected void mapSpecificFields(EmployeeHistoryDto source, EmployeeHistory destination) {
        List<WorkPeriodDto> periods = source.getPeriods();
        List<WorkPeriod> collect = periods.stream()
                .map(workPeriodDto -> workPeriodMapper.toEntity(workPeriodDto))
                .collect(Collectors.toList());
        destination.setPeriods(collect);
        super.mapSpecificFields(source, destination);
    }
}
