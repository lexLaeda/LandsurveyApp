package com.survey.mole.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.survey.mole.dto.DepartmentDto;
import com.survey.mole.mapper.DepartmentMapper;
import com.survey.mole.model.worktracker.Department;
import com.survey.mole.service.worktracker.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
class DepartmentControllerTest {

    @Mock
    private DepartmentService departmentService;

    @Mock
    private DepartmentMapper departmentMapper;

    @InjectMocks
    private DepartmentController departmentController;

    private MockMvc mockMvc;

    private static Department backend = new Department();
    private static DepartmentDto backendDto = new DepartmentDto();
    private static Long backendId = 1L;
    private static String backendName = "Backend";

    private static Department frontend = new Department();
    private static DepartmentDto frontendDto = new DepartmentDto();
    private static Long frontendId = 2L;
    private static String frontendName = "Frontend";

    private static List<Department> departments = new ArrayList<>();

    private static ObjectMapper jsonMapper = new ObjectMapper();

    private static String rootUrl = "/api/department";

    static {
        backend.setId(backendId);
        backend.setName(backendName);
        backendDto.setId(backendId);
        backendDto.setName(backendName);

        frontend.setId(frontendId);
        frontend.setName(frontendName);
        frontendDto.setId(frontendId);
        frontendDto.setName(frontendName);

        departments.add(backend);
        departments.add(frontend);
    }


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();
        Mockito.when(departmentMapper.toDto(backend)).thenReturn(backendDto);
        Mockito.when(departmentMapper.toDto(frontend)).thenReturn(frontendDto);

        Mockito.when(departmentMapper.toEntity(backendDto)).thenReturn(backend);
        Mockito.when(departmentMapper.toEntity(frontendDto)).thenReturn(frontend);
    }

    @Test
    void findDepartmentById() throws Exception {
        Mockito.when(departmentService.findById(backendId)).thenReturn(backend);
        mockMvc.perform(MockMvcRequestBuilders.get(rootUrl + "/" + backendId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(allOf(containsString(String.valueOf(backendId)),containsString(backendName))));
    }

    @Test
    void findAll() throws Exception {
        Mockito.when(departmentService.findAll()).thenReturn(departments);

        mockMvc.perform(MockMvcRequestBuilders.get(rootUrl + "/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(allOf(
                        containsString(String.valueOf(backendId)),
                        containsString(backendName),
                        containsString(String.valueOf(frontendId)),
                        containsString(frontendName)
                )));
    }

    @Test
    void addNewDepartment() throws Exception {
        Mockito.when(departmentService.save(backend)).thenReturn(backend);
        String jsonRequest = jsonMapper.writeValueAsString(backend);

        mockMvc.perform(MockMvcRequestBuilders.post(rootUrl + "/add").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(allOf(
                        containsString(String.valueOf(backendId)),
                        containsString(backendName)
                )));
    }

    @Test
    void editDepartment() throws Exception {
        Mockito.when(departmentService.update(backendId,backend)).thenReturn(backend);

        String jsonRequest = jsonMapper.writeValueAsString(backend);

        mockMvc.perform(MockMvcRequestBuilders.post(rootUrl + "/edit/" + backendId).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(allOf(
                        containsString(String.valueOf(backendId)),
                        containsString(backendName)
                )));
    }

    @Test
    void deleteDepartment() throws Exception {

        Mockito.when(departmentService.findById(backendId)).thenReturn(backend);
        Mockito.when(departmentService.delete(backend)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete(rootUrl + "/delete/" + backendId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(containsString("true")));
    }
}