package com.survey.mole.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.survey.mole.dto.PostDto;
import com.survey.mole.mapper.PostMapper;
import com.survey.mole.model.worktracker.employee.Post;
import com.survey.mole.service.worktracker.PostService;
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
import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringJUnit4ClassRunner.class)
class PostControllerTest {

    @Mock
    private PostService postService;

    @Mock
    private PostMapper postMapper;

    @InjectMocks
    private PostController postController;

    private MockMvc mockMvc;

    private static Post java = new Post();
    private static PostDto javaDto = new PostDto();
    private static Long javaId = 1L;
    private static String javaName = "java developer";

    private static Post javascript = new Post();
    private static PostDto javascriptDto = new PostDto();
    private static Long javascriptId = 2L;
    private static String javascriptName = "javascript developer";

    private static List<Post> posts = new ArrayList<>();

    private static ObjectMapper jsonMapper = new ObjectMapper();

    private static String rootURL = "/api/post";

    static {
        java.setId(javaId);
        java.setName(javaName);
        javaDto.setId(javaId);
        javaDto.setName(javaName);

        javascript.setId(javascriptId);
        javascript.setName(javascriptName);
        javascriptDto.setId(javascriptId);
        javascriptDto.setName(javascriptName);

        posts.add(java);
        posts.add(javascript);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
        Mockito.when(postMapper.toEntity(javascriptDto)).thenReturn(javascript);
        Mockito.when(postMapper.toEntity(javaDto)).thenReturn(java);
        Mockito.when(postMapper.toDto(java)).thenReturn(javaDto);
        Mockito.when(postMapper.toDto(javascript)).thenReturn(javascriptDto);
    }

    @Test
    void findPostById() throws Exception {
        Mockito.when(postService.findById(javaId)).thenReturn(java);

        mockMvc.perform(MockMvcRequestBuilders.get(rootURL + "/" + javaId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(allOf(
                        containsString(String.valueOf(javaId)),
                        containsString(javaName)
                )));
    }

    @Test
    void findAll() throws Exception {
        Mockito.when(postService.findAll()).thenReturn(posts);

        mockMvc.perform(MockMvcRequestBuilders.get(rootURL + "/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(allOf(
                        containsString(String.valueOf(javaId)),
                        containsString(javaName),
                        containsString(String.valueOf(javascriptId)),
                        containsString(javascriptName)
                )));
    }

    @Test
    void addNewPost() throws Exception {
        Mockito.when(postService.save(java)).thenReturn(java);

        String jsonRequest = jsonMapper.writeValueAsString(java);

        mockMvc.perform(MockMvcRequestBuilders.post(rootURL + "/add").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(allOf(
                        containsString(String.valueOf(javaId)),
                        containsString(javaName)
                )));
    }

    @Test
    void editPost() throws Exception {
        Mockito.when(postService.update(javaId,java)).thenReturn(java);
        String jsonRequest = jsonMapper.writeValueAsString(java);
        mockMvc.perform(MockMvcRequestBuilders.post(rootURL + "/edit/" + javaId).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(allOf(
                        containsString(String.valueOf(javaId)),
                        containsString(javaName)
                )));
    }

    @Test
    void deletePost() throws Exception {
        Mockito.when(postService.findById(javaId)).thenReturn(java);
        Mockito.when(postService.delete(java)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete(rootURL + "/delete/" + javaId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(containsString("true")));
    }

}