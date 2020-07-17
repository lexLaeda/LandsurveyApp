package com.survey.mole.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.survey.mole.dto.BaselineDto;
import com.survey.mole.dto.PointDto;
import com.survey.mole.mapper.BaselineMapper;
import com.survey.mole.model.survey.Baseline;
import com.survey.mole.model.survey.Point;
import com.survey.mole.service.BaselineService;
import com.survey.mole.service.PointService;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
class BaselineControllerTest {

    @Mock
    private BaselineService baselineService;

    @Mock
    private PointService pointService;

    @Mock
    private BaselineMapper baselineMapper;

    @InjectMocks
    private BaselineController baselineController;

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

    private static Point pz4040 = new Point();
    private static Long pz4040Id = 4L;
    private static String pz4040Name = "pz4040";
    private static Double pz4040xCoord = 404.22022;
    private static Double pz4040yCoord = 42412.22022;
    private static Double pz4040hCoord = 40.22022;
    private static PointDto pz4040Dto = new PointDto();

    private static Baseline sst = new Baseline();
    private static Long sstId = 1L;
    private static String sstName = "SST";
    private static BaselineDto sstDto = new BaselineDto();

    private static Baseline pst = new Baseline();
    private static Long pstId = 2L;
    private static String pstName = "PST";
    private static BaselineDto pstDto = new BaselineDto();

    private static List<Baseline> baselines = new ArrayList<>();

    private static String rootURL = "/api/baseline";

    private static ObjectMapper jsonMapper = new ObjectMapper();
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

        pz4040.setId(pz4040Id);
        pz4040.setName(pz4040Name);
        pz4040.setX(pz4040xCoord);
        pz4040.setY(pz4040yCoord);
        pz4040.setH(pz3030hCoord);

        pz3030Dto.setId(pz3030Id);
        pz3030Dto.setName(pz3030Name);
        pz3030Dto.setX(pz3030xCoord);
        pz3030Dto.setY(pz3030yCoord);
        pz3030Dto.setH(pz3030hCoord);

        sst.setId(sstId);
        sst.setName(sstName);
        sst.setPoints(Arrays.asList(pz2020,pz3030));

        sstDto.setId(sstId);
        sstDto.setName(sstName);
        sstDto.setPointStart(pz2020Dto);
        sstDto.setPointEnd(pz3030Dto);

        pst.setId(pstId);
        pst.setName(pstName);
        pst.setPoints(Arrays.asList(pz3030,pz4040));

        pstDto.setId(pstId);
        pstDto.setName(pstName);
        pstDto.setPointStart(pz3030Dto);
        pstDto.setPointEnd(pz4040Dto);

        baselines.add(sst);
        baselines.add(pst);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(baselineController).build();
        Mockito.when(baselineMapper.toDto(sst)).thenReturn(sstDto);
        Mockito.when(baselineMapper.toDto(pst)).thenReturn(pstDto);
        Mockito.when(baselineMapper.toEntity(sstDto)).thenReturn(sst);
        Mockito.when(baselineMapper.toEntity(pstDto)).thenReturn(pst);
    }

    @Test
    void findBaselineById() throws Exception {
        Mockito.when(baselineService.findById(sstId)).thenReturn(sst);

        mockMvc.perform(MockMvcRequestBuilders.get(rootURL + "/" + sstId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(allOf(
                        containsString(String.valueOf(sstId)),
                        containsString(sstName),
                        containsString(String.valueOf(pz2020Id)),
                        containsString(pz2020Name),
                        containsString(String.valueOf(pz2020xCoord)),
                        containsString(String.valueOf(pz2020yCoord)),
                        containsString(String.valueOf(pz2020hCoord))
                        )));
    }

    @Test
    void findAll() throws Exception {
        Mockito.when(baselineService.findAll()).thenReturn(baselines);

        mockMvc.perform(MockMvcRequestBuilders.get(rootURL + "/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(allOf(
                        containsString(String.valueOf(sstId)),
                        containsString(sstName),
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
    void findByPoint() throws Exception {
        Mockito.when(pointService.findById(pz3030Id)).thenReturn(pz3030);
        Mockito.when(baselineService.findByPoint(pz3030)).thenReturn(baselines);

        mockMvc.perform(MockMvcRequestBuilders.get(rootURL + "/by_point/" + pz3030Id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(allOf(
                        containsString(String.valueOf(sstId)),
                        containsString(sstName),
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
    void addNewBaseline() throws Exception {
        Mockito.when(baselineService.save(sst)).thenReturn(sst);
        String jsonRequest = jsonMapper.writeValueAsString(sstDto);

        mockMvc.perform(MockMvcRequestBuilders.post(rootURL + "/add")
                .contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(allOf(
                        containsString(String.valueOf(sstId)),
                        containsString(sstName),
                        containsString(String.valueOf(pz2020Id)),
                        containsString(pz2020Name),
                        containsString(String.valueOf(pz2020xCoord)),
                        containsString(String.valueOf(pz2020yCoord)),
                        containsString(String.valueOf(pz2020hCoord))
                )));
    }

    @Test
    void editBaseline() throws Exception {
        Mockito.when(baselineService.update(sstId,sst)).thenReturn(sst);
        String jsonRequest = jsonMapper.writeValueAsString(sstDto);

        mockMvc.perform(MockMvcRequestBuilders.post(rootURL + "/edit/" + sstId)
                .contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(allOf(
                        containsString(String.valueOf(sstId)),
                        containsString(sstName),
                        containsString(String.valueOf(pz2020Id)),
                        containsString(pz2020Name),
                        containsString(String.valueOf(pz2020xCoord)),
                        containsString(String.valueOf(pz2020yCoord)),
                        containsString(String.valueOf(pz2020hCoord))
                )));
    }

    @Test
    void deleteBaseline() throws Exception {
        Mockito.when(baselineService.findById(sstId)).thenReturn(sst);
        Mockito.when(baselineService.delete(sst)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete(rootURL + "/delete/" + sstId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(containsString("true")));
    }

    @Test
    void deleteBaselineList() throws Exception {
        List<BaselineDto> blDeleteRequest = Arrays.asList(sstDto,pstDto);
        String jsonRequest = jsonMapper.writeValueAsString(blDeleteRequest);
        Mockito.when(baselineService.deleteList(baselines)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post(rootURL + "/delete-list").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string("true"));
    }
}