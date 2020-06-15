package com.survey.mole.mapper;

import com.survey.mole.dto.DepartmentDto;
import com.survey.mole.model.worktracker.Department;
import com.survey.mole.model.worktracker.employee.Employee;
import com.survey.mole.service.worktracker.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentMapper extends AbstractMapper<Department, DepartmentDto> {

    private ModelMapper modelMapper;

    private EmployeeService employeeService;

    @Autowired
    public DepartmentMapper(ModelMapper modelMapper, EmployeeService employeeService) {
        super(modelMapper,Department.class,DepartmentDto.class);
        this.modelMapper = modelMapper;
        this.employeeService = employeeService;
    }

    @PostConstruct
    public void initMapper(){
        modelMapper.createTypeMap(Department.class,DepartmentDto.class)
                .setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(DepartmentDto.class,Department.class)
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(Department source, DepartmentDto destination) {
        List<Long> collect = employeeService.findByDepartment(source).stream()
                .map(Employee::getId)
                .collect(Collectors.toList());
        destination.setEmployees(collect);
    }

    @Override
    protected void mapSpecificFields(DepartmentDto source, Department destination) {
        List<Employee> collect = source.getEmployees().stream()
                .map(id -> employeeService.findById(id)).collect(Collectors.toList());
        destination.setEmployees(collect);
    }
}
