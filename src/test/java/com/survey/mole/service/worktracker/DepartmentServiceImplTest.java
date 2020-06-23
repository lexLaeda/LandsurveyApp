package com.survey.mole.service.worktracker;

import com.survey.mole.exception.ElementNotFoundException;
import com.survey.mole.model.worktracker.Department;
import com.survey.mole.model.worktracker.Holiday;
import com.survey.mole.repository.worktracker.employee.DepartmentRepository;
import org.junit.Assert;
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

class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepository repository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private static Department one = new Department(1L,"one",new ArrayList<>());
    private static Department two = new Department(2L,"two",new ArrayList<>());
    private static Department three = new Department(3L,"three",new ArrayList<>());

    private static List<Department> departments = new ArrayList<>();

    static {
        departments.add(one);
        departments.add(two);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void save() {
        Mockito.when(repository.saveAndFlush(three)).thenReturn(three);
        assertEquals(three,departmentService.save(three));
    }

    @Test
    void update() {
        two.setName("update");
        Mockito.when(repository.saveAndFlush(two)).thenReturn(two);
        Mockito.when(repository.findById(2L)).thenReturn(Optional.of(two));
        assertEquals(two,departmentService.update(2L,two));
    }

    @Test
    void findById() {
        Mockito.when(repository.findById(2L)).thenReturn(Optional.of(two));
        assertEquals(two,departmentService.findById(2L));
    }

    @Test
    void findByFakeId() {
        Mockito.when(repository.findById(4L)).thenReturn(Optional.ofNullable(null));
        ElementNotFoundException elementNotFoundException = assertThrows(ElementNotFoundException.class, () -> departmentService.findById(4L));
        assertTrue(elementNotFoundException.getMessage().contains("Department"));
        assertTrue(elementNotFoundException.getMessage().contains("4"));
    }

    @Test
    void findAll() {
        Mockito.when(repository.findAll()).thenReturn(departments);
        assertEquals(departments,departmentService.findAll());
    }

    @Test
    void delete() {
        Mockito.when(repository.count()).thenReturn(2L);
        Mockito.doAnswer(invocation -> {
            Department department = invocation.getArgument(0);
            department.setId(-1L);
            return null;
        }).when(repository).delete(two);

        Assert.assertFalse(departmentService.delete(two));
    }

}