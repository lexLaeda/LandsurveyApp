package com.survey.mole.mapper;

import com.survey.mole.dto.EmployeeDto;
import com.survey.mole.dto.EmployeeHistoryDto;
import com.survey.mole.model.worktracker.Department;
import com.survey.mole.model.worktracker.employee.Employee;
import com.survey.mole.model.worktracker.employee.EmployeeHistory;
import com.survey.mole.model.worktracker.employee.Post;
import com.survey.mole.service.worktracker.DepartmentService;
import com.survey.mole.service.worktracker.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class EmployeeMapper extends AbstractMapper<Employee, EmployeeDto> {

    private ModelMapper modelMapper;
    private DepartmentService departmentService;
    private PostService postService;
    private EmployeeHistoryMapper employeeHistoryMapper;

    @Autowired
    public EmployeeMapper(ModelMapper modelMapper, DepartmentService departmentService, PostService postService,
                          EmployeeHistoryMapper employeeHistoryMapper) {
        super(modelMapper,Employee.class , EmployeeDto.class);
        this.modelMapper=modelMapper;
        this.departmentService = departmentService;
        this.postService = postService;
        this.employeeHistoryMapper = employeeHistoryMapper;
    }

    @PostConstruct
    public void initMapper(){
        modelMapper.createTypeMap(Employee.class,EmployeeDto.class).setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(EmployeeDto.class,Employee.class).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(Employee source, EmployeeDto destination) {
        EmployeeHistory employeeHistory = source.getEmployeeHistory();
        destination.setDepartmentId(source.getDepartment().getId());
        destination.setPostId(source.getPost().getId());
        destination.setEmployeeHistoryDto(employeeHistoryMapper.toDto(employeeHistory));
    }

    @Override
    protected void mapSpecificFields(EmployeeDto source, Employee destination) {
        Department department = departmentService.findById(source.getDepartmentId());
        Post post = postService.findById(source.getPostId());
        EmployeeHistoryDto employeeHistoryDto = source.getEmployeeHistoryDto();
        destination.setDepartment(department);
        destination.setPost(post);
        destination.setEmployeeHistory(employeeHistoryMapper.toEntity(employeeHistoryDto));
    }
}
