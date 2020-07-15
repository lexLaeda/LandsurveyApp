package com.survey.mole.model.worktracker.employee;

import com.survey.mole.model.worktracker.Department;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeHistoryTest {

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
    void addNewPeriod() {
        tomHistory.addNewPeriod(frontendPeriod);

        assertTrue(tomHistory.getPeriods().contains(frontendPeriod));
    }



    @Test
    @Order(2)
    void getLastPeriod() {
        tomHistory.addNewPeriod(backendPeriod);
        assertEquals(backendPeriod,tomHistory.getLastPeriod());
    }

    @Test
    @Order(3)
    void closePeriod() {
        LocalDate endOfBackend = LocalDate.now();

        backendPeriod.setEnd(endOfBackend);

        tomHistory.closeLastPeriod(endOfBackend.minusDays(1));

        assertEquals(backendPeriod,tomHistory.getLastPeriod());
    }
}