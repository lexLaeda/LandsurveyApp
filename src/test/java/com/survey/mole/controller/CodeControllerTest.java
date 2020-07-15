package com.survey.mole.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.survey.mole.model.worktracker.Code;
import com.survey.mole.service.worktracker.CodeService;
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

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;

@RunWith(SpringJUnit4ClassRunner.class)
class CodeControllerTest {

    @Mock
    private CodeService service;

    @InjectMocks
    private CodeController controller;

    private MockMvc mockMvc;


    private static Code codeOne = new Code();
    private static Long oneId = 1L;
    private static String oneStatus = "O";
    private static String oneDescription = "One description";


    private static Code codeTwo = new Code();
    private static Long twoId = 2L;
    private static String twoStatus = "T";
    private static String twoDescription = "Two description";

    private static List<Code> codes = new ArrayList<>();

    private static String rootURL = "/api/code";

    private ObjectMapper jsonMapper = new ObjectMapper();

    static {
        codeOne.setId(oneId);
        codeOne.setDescription(oneDescription);
        codeOne.setStatus(oneStatus);

        codeTwo.setId(twoId);
        codeTwo.setDescription(twoDescription);
        codeTwo.setStatus(twoStatus);

        codes.add(codeOne);
        codes.add(codeTwo);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void findCodeById() throws Exception {
        Mockito.when(service.findById(oneId)).thenReturn(codeOne);
        mockMvc.perform(MockMvcRequestBuilders.get(rootURL + "/" +oneId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(allOf(
                        containsString(String.valueOf(oneId)),
                        containsString(oneStatus),
                        containsString(oneDescription))
                ));
    }

    @Test
    void findAll() throws Exception {
        Mockito.when(service.findAll()).thenReturn(codes);
        mockMvc.perform(MockMvcRequestBuilders.get(rootURL + "/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(allOf(
                        containsString(String.valueOf(oneId)),
                        containsString(oneStatus),
                        containsString(oneDescription),
                        containsString(String.valueOf(twoId)),
                        containsString(twoStatus),
                        containsString(twoDescription))
                ));
    }

    @Test
    void addNewCode() throws Exception {
        Mockito.when(service.save(codeOne)).thenReturn(codeOne);
        String jsonRequest = jsonMapper.writeValueAsString(codeOne);
        mockMvc.perform(MockMvcRequestBuilders.post(rootURL + "/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(allOf(
                        containsString(String.valueOf(oneId)),
                        containsString(oneStatus),
                        containsString(oneDescription))));
    }

    @Test
    void editBaseline() throws Exception {
        Mockito.when(service.update(oneId,codeOne)).thenReturn(codeOne);
        String jsonRequest = jsonMapper.writeValueAsString(codeOne);
        mockMvc.perform(MockMvcRequestBuilders.post(rootURL + "/edit/" + oneId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(allOf(
                        containsString(String.valueOf(oneId)),
                        containsString(oneStatus),
                        containsString(oneDescription))));
    }

    @Test
    void deleteBaseline() throws Exception {
        Mockito.when(service.findById(oneId)).thenReturn(codeOne);
        Mockito.when(service.delete(codeOne)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete(rootURL + "/delete/" + oneId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("true")));
    }
}