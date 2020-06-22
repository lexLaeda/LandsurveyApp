package com.survey.mole.service.worktracker;

import com.survey.mole.exception.ElementNotFoundException;
import com.survey.mole.model.worktracker.Department;
import com.survey.mole.repository.worktracker.employee.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository repository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Department save(Department department) {
        return repository.saveAndFlush(department);
    }

    @Override
    public Department update(Long id, Department department) {
        Department byId = findById(id);
        department.setId(id);
        return repository.saveAndFlush(department);
    }

    @Override
    public Department findById(Long id) {
        return repository.findById(id).orElseThrow(()->new ElementNotFoundException("Department with id " + id + " not found"));
    }

    @Override
    public List<Department> findAll() {
        return repository.findAll();
    }

    @Override
    public Boolean delete(Department department) {
        long before = repository.count();
        repository.delete(department);
        long after = repository.count();
        return after - before == 1;
    }
}
