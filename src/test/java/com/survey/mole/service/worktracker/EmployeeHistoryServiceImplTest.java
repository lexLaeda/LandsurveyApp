package com.survey.mole.service.worktracker;

import com.survey.mole.model.worktracker.Department;
import com.survey.mole.model.worktracker.employee.Employee;
import com.survey.mole.model.worktracker.employee.EmployeeHistory;
import com.survey.mole.model.worktracker.employee.WorkPeriod;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeHistoryServiceImplTest {



    private EmployeeHistoryService employeeHistoryService = new EmployeeHistoryServiceImpl();

    private static Employee tom = new Employee();

    private static EmployeeHistory tomHistory = new EmployeeHistory();

    private static WorkPeriod frontendPeriod = new WorkPeriod();

    private static WorkPeriod backendPeriod = new WorkPeriod();

    private static Department backend = new Department();

    private static Department frontend = new Department();

    private static LocalDate startOfBackend = LocalDate.now();

    private static LocalDate startOfFrontend;

    private static Long backendId = 2L;

    private static Long frontendId = 1L;

    static {
        backend.setName("Backend");
        backendPeriod.setDepartment(backend);
        backendPeriod.setStart(startOfBackend);
        backendPeriod.setId(backendId);
        startOfFrontend = startOfBackend.minusYears(1);

        frontend.setName("Frontend");
        frontendPeriod.setStart(startOfFrontend);
        frontendPeriod.setDepartment(frontend);
        frontendPeriod.setId(frontendId);
        frontendPeriod.setEnd(startOfBackend.minusDays(1));

        tom.setEmployeeHistory(tomHistory);
        tomHistory.setEmployee(tom);
    }
    @Test
    @Order(1)
    void createEmployeeHistory() {
        EmployeeHistory tomHistory = employeeHistoryService.createEmployeeHistory(tom, frontend, startOfFrontend);
        tomHistory.getLastPeriod().setId(1L);

        tom.setEmployeeHistory(tomHistory);

        assertEquals(tom,tomHistory.getEmployee());
        assertEquals(1, tomHistory.getPeriods().size());
        assertEquals(frontend, tomHistory.getLastPeriod().getDepartment());
        assertEquals(startOfFrontend,tomHistory.getLastPeriod().getStart());
        assertNull(tomHistory.getLastPeriod().getEnd());
    }

    @Test
    @Order(2)
    void updateHistory() {

        EmployeeHistory employeeHistory = employeeHistoryService.updateHistory(tom, backend, startOfBackend);

        assertEquals(tom,employeeHistory.getEmployee());

        assertEquals(2,employeeHistory.getPeriods().size());

    }
}