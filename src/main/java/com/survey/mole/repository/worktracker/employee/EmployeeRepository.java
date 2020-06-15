package com.survey.mole.repository.worktracker.employee;

import com.survey.mole.model.worktracker.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
