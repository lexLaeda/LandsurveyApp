package com.survey.mole.service.worktracker;

import com.survey.mole.exception.ElementNotFoundException;
import com.survey.mole.model.worktracker.Department;
import com.survey.mole.model.worktracker.employee.Employee;
import com.survey.mole.repository.worktracker.employee.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private static Employee one = new Employee();
    private static Employee two = new Employee();
    private static Employee three = new Employee();
    private static Department department = new Department();

    private static List<Employee> employees = new ArrayList<>();

    static {
        department.setName("department");
        one.setId(1L);
        one.setFirstName("one");
        one.setDepartment(department);
        employees.add(one);
        two.setId(2L);
        two.setFirstName("two");
        employees.add(two);
        three.setId(3L);
        three.setFirstName("three");

    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void save() {
        Mockito.when(repository.saveAndFlush(three)).thenReturn(three);
        assertEquals(three, employeeService.save(three));
    }

    @Test
    void update() {
        two.setFirstName("update");
        Mockito.when(repository.findById(2L)).thenReturn(Optional.of(two));
        Mockito.when(repository.saveAndFlush(two)).thenReturn(two);
        assertEquals(two, employeeService.update(2L, two));
    }

    @Test
    void findById() {
        Mockito.when(repository.findById(2L)).thenReturn(Optional.of(two));
        assertEquals(two, employeeService.findById(2L));
    }

    @Test
    void findByFakeId() {
        Mockito.when(repository.findById(4L)).thenReturn(Optional.ofNullable(null));
        ElementNotFoundException elementNotFoundException = assertThrows(ElementNotFoundException.class, () -> employeeService.findById(4L));
        assertTrue(elementNotFoundException.getMessage().contains("Employee"));
        assertTrue(elementNotFoundException.getMessage().contains("4"));
    }

    @Test
    void findAll() {
        Mockito.when(repository.findAll()).thenReturn(employees);
        assertEquals(employees, employeeService.findAll());
    }

    @Test
    void findByDepartment() {
        Mockito.when(repository.findAllByDepartment(department)).thenReturn(employees);
        assertEquals(employees, employeeService.findByDepartment(department));
    }


    @Test
    void delete() {
        Mockito.when(repository.count()).thenReturn(2L);
        Mockito.doAnswer(invocation -> {
            Employee employee = invocation.getArgument(0);
            employee.setId(-1L);
            return null;
        }).when(repository).delete(two);

        assertFalse(employeeService.delete(two));
    }
}