package com.survey.mole.repository.worktracker.employee;

import com.survey.mole.model.worktracker.employee.EmployeeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeHistoryRepository extends JpaRepository<EmployeeHistory,Long> {
}
