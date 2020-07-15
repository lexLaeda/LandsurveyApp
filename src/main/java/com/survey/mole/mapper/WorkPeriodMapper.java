package com.survey.mole.mapper;

import com.survey.mole.dto.WorkPeriodDto;
import com.survey.mole.model.worktracker.Department;
import com.survey.mole.model.worktracker.employee.WorkPeriod;
import com.survey.mole.service.worktracker.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class WorkPeriodMapper extends AbstractMapper<WorkPeriod, WorkPeriodDto> {

    private ModelMapper modelMapper;

    private DepartmentService departmentService;

    @Autowired
    public WorkPeriodMapper(ModelMapper modelMapper, DepartmentService departmentService) {
        super(modelMapper, WorkPeriod.class, WorkPeriodDto.class);
        this.modelMapper = modelMapper;
        this.departmentService = departmentService;
    }

    @PostConstruct
    public void initMapper(){
        modelMapper.createTypeMap(WorkPeriod.class,WorkPeriodDto.class).setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(WorkPeriodDto.class, WorkPeriod.class).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(WorkPeriod source, WorkPeriodDto destination) {
        Department department = source.getDepartment();
        destination.setDepartmentId(department.getId());
        super.mapSpecificFields(source, destination);
    }

    @Override
    protected void mapSpecificFields(WorkPeriodDto source, WorkPeriod destination) {
        Department byId = departmentService.findById(source.getId());
        destination.setDepartment(byId);
        super.mapSpecificFields(source, destination);
    }
}
