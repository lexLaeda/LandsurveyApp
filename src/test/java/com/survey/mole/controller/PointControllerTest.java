package com.survey.mole.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.survey.mole.dto.LevelReferenceDto;
import com.survey.mole.dto.PairRequest;
import com.survey.mole.dto.PointDto;
import com.survey.mole.mapper.LevelReferenceMapper;
import com.survey.mole.mapper.PointMapper;
import com.survey.mole.model.survey.LevelReference;
import com.survey.mole.model.survey.Point;
import com.survey.mole.service.PointService;
import org.junit.experimental.results.ResultMatchers;
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
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
class PointControllerTest {

    @Mock
    private LevelReferenceMapper levelReferenceMapper;

    @Mock
    private PointService pointService;

    @Mock
    private PointMapper pointMapper;

    @InjectMocks
    private PointController pointController;

    private MockMvc mockMvc;

    private static Point pz2020 = new Point();
    private static Long pz2020Id = 1L;
    private static String pz2020Name = "pz2020";
    private static Double pz2020xCoord = 202.22022;
    private static Double pz2020yCoord = 202.22022;
    private static Double pz2020hCoord = 202.22022;
    private static PointDto pz2020Dto = new PointDto();

    private static Point pz3030 = new Point();
    private static Long pz3030Id = 2L;
    private static String pz3030Name = "pz3030";
    private static Double pz3030xCoord = 303.22022;
    private static Double pz3030yCoord = 3030.22022;
    private static Double pz3030hCoord = 330.22022;
    private static PointDto pz3030Dto = new PointDto();

    private static List<Point> points = new ArrayList<>();

    private static String rootURL = "/api/point";

    private static ObjectMapper jsonMapper = new ObjectMapper();

    private static LevelReference lr = new LevelReference();
    private static LevelReferenceDto lrDto = new LevelReferenceDto();

    private static Long lrId = 1000L;
    private static String lrName = pz2020Name;

    static {
        pz2020.setId(pz2020Id);
        pz2020.setName(pz2020Name);
        pz2020.setX(pz2020xCoord);
        pz2020.setY(pz2020yCoord);
        pz2020.setH(pz2020hCoord);

        pz2020Dto.setId(pz2020Id);
        pz2020Dto.setName(pz2020Name);
        pz2020Dto.setX(pz2020xCoord);
        pz2020Dto.setY(pz2020yCoord);
        pz2020Dto.setH(pz2020hCoord);

        pz3030.setId(pz3030Id);
        pz3030.setName(pz3030Name);
        pz3030.setX(pz3030xCoord);
        pz3030.setY(pz3030yCoord);
        pz3030.setH(pz3030hCoord);

        pz3030Dto.setId(pz3030Id);
        pz3030Dto.setName(pz3030Name);
        pz3030Dto.setX(pz3030xCoord);
        pz3030Dto.setY(pz3030yCoord);
        pz3030Dto.setH(pz3030hCoord);

        points.add(pz2020);
        points.add(pz3030);

        lr.setId(lrId);
        lr.setName(lrName);
        lr.setElevation(pz2020hCoord);

        lrDto.setId(lrId);
        lrDto.setName(lrName);
        lrDto.setElevation(pz2020hCoord);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pointController).build();

        Mockito.when(pointMapper.toDto(pz2020)).thenReturn(pz2020Dto);
        Mockito.when(pointMapper.toDto(pz3030)).thenReturn(pz3030Dto);
        Mockito.when(pointMapper.toEntity(pz2020Dto)).thenReturn(pz2020);
        Mockito.when(pointMapper.toEntity(pz3030Dto)).thenReturn(pz3030);
    }

    @Test
    void findPointById() throws Exception {
        Mockito.when(pointService.findById(pz2020Id)).thenReturn(pz2020);

        mockMvc.perform(MockMvcRequestBuilders.get(rootURL + "/" + pz2020Id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(allOf(
                        containsString(String.valueOf(pz2020Id)),
                        containsString(pz2020Name),
                        containsString(String.valueOf(pz2020xCoord)),
                        containsString(String.valueOf(pz2020yCoord)),
                        containsString(String.valueOf(pz2020hCoord))
                )));
    }

    @Test
    void findAll() throws Exception {
        Mockito.when(pointService.findAll()).thenReturn(points);

        mockMvc.perform(MockMvcRequestBuilders.get(rootURL + "/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(allOf(
                        containsString(String.valueOf(pz2020Id)),
                        containsString(pz2020Name),
                        containsString(String.valueOf(pz2020xCoord)),
                        containsString(String.valueOf(pz2020yCoord)),
                        containsString(String.valueOf(pz2020hCoord)),
                        containsString(String.valueOf(pz3030Id)),
                        containsString(pz3030Name),
                        containsString(String.valueOf(pz3030xCoord)),
                        containsString(String.valueOf(pz3030yCoord)),
                        containsString(String.valueOf(pz3030hCoord))
                )));
    }

    @Test
    void savePoint() throws Exception {
        Mockito.when(pointService.save(pz2020)).thenReturn(pz2020);
        String jsonRequest = jsonMapper.writeValueAsString(pz2020Dto);

        mockMvc.perform(MockMvcRequestBuilders.post(rootURL + "/add").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(allOf(
                        containsString(String.valueOf(pz2020Id)),
                        containsString(pz2020Name),
                        containsString(String.valueOf(pz2020xCoord)),
                        containsString(String.valueOf(pz2020yCoord)),
                        containsString(String.valueOf(pz2020hCoord))
                )));

    }

    @Test
    void savePointAndLR() throws Exception {
        PairRequest request = new PairRequest();
        request.setPointDto(pz2020Dto);
        request.setLevelReferenceDto(lrDto);

        String jsonRequest = jsonMapper.writeValueAsString(request);

        Mockito.when(levelReferenceMapper.toDto(lr)).thenReturn(lrDto);
        Mockito.when(levelReferenceMapper.toEntity(lrDto)).thenReturn(lr);
        Mockito.when(pointService.save(pz2020)).thenReturn(pz2020);

        mockMvc.perform(MockMvcRequestBuilders.post(rootURL + "/add-pair").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(allOf(
                        containsString(String.valueOf(pz2020Id)),
                        containsString(pz2020Name),
                        containsString(String.valueOf(pz2020xCoord)),
                        containsString(String.valueOf(pz2020yCoord)),
                        containsString(String.valueOf(pz2020hCoord)),
                        containsString(String.valueOf(lrId))
                )));
    }

    @Test
    void editPoint() throws Exception {
        Mockito.when(pointService.update(pz2020Id,pz2020)).thenReturn(pz2020);
        String jsonRequest = jsonMapper.writeValueAsString(pz2020Dto);

        mockMvc.perform(MockMvcRequestBuilders.post(rootURL + "/edit/" + pz2020Id).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(allOf(
                        containsString(String.valueOf(pz2020Id)),
                        containsString(pz2020Name),
                        containsString(String.valueOf(pz2020xCoord)),
                        containsString(String.valueOf(pz2020yCoord)),
                        containsString(String.valueOf(pz2020hCoord))
                )));

    }

    @Test
    void deletePoint() throws Exception {

        Mockito.when(pointService.findById(pz2020Id)).thenReturn(pz2020);
        Mockito.when(pointService.delete(pz2020)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete(rootURL + "/delete/" + pz2020Id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(containsString("true")));
    }
}