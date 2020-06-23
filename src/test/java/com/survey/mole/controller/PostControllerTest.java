package com.survey.mole.controller;

import com.survey.mole.MoleApplication;
import com.survey.mole.dto.PostDto;
import com.survey.mole.mapper.PostMapper;
import com.survey.mole.model.worktracker.employee.Post;
import com.survey.mole.service.worktracker.PostService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.List;



class PostControllerTest {


    private WebTestClient webTestClient;



    private PostService service;


    private PostMapper postMapper;


    private static Post one = new Post(1L, "one", new ArrayList<>());
    private static PostDto oneDto = new PostDto();
    private static Post two = new Post(2L, "two", new ArrayList<>());
    private static PostDto twoDto = new PostDto();
    private static Post three = new Post(3L, "three", new ArrayList<>());

    private static List<Post> posts = new ArrayList<>();

    static {
        posts.add(one);
        posts.add(two);

        oneDto.setId(one.getId());
        oneDto.setName(one.getName());

        twoDto.setId(two.getId());
        twoDto.setName(two.getName());
    }


}