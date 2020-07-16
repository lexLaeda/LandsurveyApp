package com.survey.mole.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.survey.mole.model.worktracker.Holiday;
import com.survey.mole.service.worktracker.HolidayService;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
class HolidayControllerTest {

    @Mock
    private HolidayService service;

    @InjectMocks
    private HolidayController holidayController;

    private MockMvc mockMvc;

    private static Holiday newYear = new Holiday();
    private static Long newYearId = 1L;
    private static String newYearName = "New Year";
    private static LocalDate newYearDate = LocalDate.of(2020,1,1);

    private static Holiday victoryDay = new Holiday();
    private static Long victoryDayId = 2L;
    private static String victoryDayName = "Victory Day";
    private static LocalDate victoryDayDate = LocalDate.of(2020,5,9);

    private static final String rootURL = "/api/holiday";

    private static List<Holiday> holidays = new ArrayList<>();

    private static ObjectMapper jsonMapper = new ObjectMapper();

    static {
        newYear.setId(newYearId);
        newYear.setName(newYearName);
        newYear.setDate(newYearDate);

        victoryDay.setId(victoryDayId);
        victoryDay.setName(victoryDayName);
        victoryDay.setDate(victoryDayDate);

        holidays.add(newYear);
        holidays.add(victoryDay);
        jsonMapper.registerModule(new JavaTimeModule());
        jsonMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(holidayController).build();

    }

    @Test
    void findHolidayById() throws Exception {
        Mockito.when(service.findById(newYearId)).thenReturn(newYear);

        mockMvc.perform(MockMvcRequestBuilders.get(rootURL + "/" + newYearId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(allOf(
                        containsString(String.valueOf(newYearId)),
                                containsString(newYearName),
                                containsString("2020-01-01")
                )));
    }

    @Test
    void findAll() throws Exception {
        Mockito.when(service.findAll()).thenReturn(holidays);
        mockMvc.perform(MockMvcRequestBuilders.get(rootURL + "/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(allOf(
                        containsString(String.valueOf(newYearId)),
                        containsString(newYearName),
                        containsString("2020-01-01"),
                        containsString(String.valueOf(victoryDayId)),
                        containsString(victoryDayName),
                        containsString("2020-05-09")
                )));
    }

    @Test
    void addNewHoliday() throws Exception {
        Mockito.when(service.save(newYear)).thenReturn(newYear);
        String jsonRequest = jsonMapper.writeValueAsString(newYear);

        mockMvc.perform(MockMvcRequestBuilders.post(rootURL + "/add").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(allOf(
                        containsString(String.valueOf(newYearId)),
                        containsString(newYearName),
                        containsString("2020-01-01")
                )));
    }

    @Test
    void editHoliday() throws Exception {
        Mockito.when(service.update(newYearId,newYear)).thenReturn(newYear);

        String jsonRequest = jsonMapper.writeValueAsString(newYear);


        mockMvc.perform(MockMvcRequestBuilders.post(rootURL + "/edit/" + newYearId).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(allOf(
                        containsString(String.valueOf(newYearId)),
                        containsString(newYearName),
                        containsString("2020-01-01")
                )));
    }

    @Test
    void deleteHoliday() throws Exception {
        Mockito.when(service.findById(newYearId)).thenReturn(newYear);
        Mockito.when(service.delete(newYear)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete(rootURL + "/delete/" + newYearId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(containsString("true")));
    }
}