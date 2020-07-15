package com.survey.mole.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.survey.mole.dto.LevelReferenceDto;
import com.survey.mole.mapper.LevelReferenceMapper;
import com.survey.mole.model.survey.LevelReference;
import com.survey.mole.service.LevelReferenceService;
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

import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
class LevelReferenceControllerTest {

    @Mock
    private LevelReferenceService service;

    @Mock
    private LevelReferenceMapper mapper;

    @InjectMocks
    private LevelReferenceController controller;

    private MockMvc mockMvc;

    private static LevelReference rpOne = new LevelReference();
    private static LevelReferenceDto rpOneDto = new LevelReferenceDto();

    private static Long rpOneId = 1L;
    private static String oneName = "rpOne";
    private static Double oneElevation = 20.293;

    private static LevelReference rpTwo = new LevelReference();
    private static LevelReferenceDto rpTwoDto = new LevelReferenceDto();

    private static Long rpTwoId = 2L;
    private static String twoName = "rpOne";
    private static Double twoElevation = 20.293;

    private static List<LevelReference> list = new ArrayList<>();

    private static final String rootURL = "/api/level-reference";

    private ObjectMapper jsonMapper = new ObjectMapper();

    static {
        rpOne.setId(rpOneId);
        rpOne.setName(oneName);
        rpOne.setElevation(oneElevation);

        rpOneDto.setId(rpOneId);
        rpOneDto.setName(oneName);
        rpOneDto.setElevation(oneElevation);

        rpTwo.setId(rpTwoId);
        rpTwo.setName(twoName);
        rpTwo.setElevation(twoElevation);

        rpTwoDto.setId(rpTwoId);
        rpTwoDto.setName(twoName);
        rpTwoDto.setElevation(twoElevation);

        list.add(rpOne);
        list.add(rpTwo);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        Mockito.when(mapper.toDto(rpTwo)).thenReturn(rpTwoDto);
        Mockito.when(mapper.toDto(rpOne)).thenReturn(rpOneDto);
        Mockito.when(mapper.toEntity(rpOneDto)).thenReturn(rpOne);
        Mockito.when(mapper.toEntity(rpTwoDto)).thenReturn(rpTwo);
    }

    @Test
    void findLevelReferenceById() throws Exception {
        Mockito.when(service.findById(1L)).thenReturn(rpOne);
        mockMvc.perform(MockMvcRequestBuilders.get(rootURL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(allOf(
                        startsWith("{"),
                        endsWith("}"),
                        containsString(oneName),
                        containsString(String.valueOf(oneElevation))
                )));
    }

    @Test
    void findAll() throws Exception {

        Mockito.when(service.findAll()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get(rootURL + "/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(allOf(
                        startsWith("["),
                        endsWith("]"),
                        containsString(oneName),
                        containsString(twoName),
                        containsString(String.valueOf(oneElevation))
                        , containsString(String.valueOf(twoElevation))
                )));
    }

    @Test
    void addNewLevelReference() throws Exception {
        String requestBody = jsonMapper.writeValueAsString(rpOne);
        Mockito.when(service.save(rpOne)).thenReturn(rpOne);
        mockMvc.perform(MockMvcRequestBuilders.post(rootURL + "/add")
                .contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(allOf(
                        startsWith("{"),
                        endsWith("}"),
                        containsString(oneName),
                        containsString(String.valueOf(oneElevation))
                )));
    }

    @Test
    void editLevelReference() throws Exception {
        Mockito.when(service.update(rpOneId, rpOne)).thenReturn(rpOne);
        String requestBody = jsonMapper.writeValueAsString(rpOne);
        mockMvc.perform(MockMvcRequestBuilders.post(rootURL + "/edit/" + rpOneId)
                .contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(allOf(
                        startsWith("{"),
                        endsWith("}"),
                        containsString(oneName),
                        containsString(String.valueOf(oneElevation))
                )));
    }

    @Test
    void deleteLevelReference() throws Exception {
        Mockito.when(service.delete(rpOne)).thenReturn(true);
        Mockito.when(service.findById(rpOneId)).thenReturn(rpOne);
        mockMvc.perform(MockMvcRequestBuilders.delete(rootURL + "/delete/" + rpOneId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("true")));
    }
}