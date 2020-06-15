package com.survey.mole.repository.worktracker.employee;

import com.survey.mole.model.worktracker.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
