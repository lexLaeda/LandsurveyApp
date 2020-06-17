package com.survey.mole.controller;

import com.survey.mole.dto.PostDto;
import com.survey.mole.mapper.PostMapper;
import com.survey.mole.model.worktracker.employee.Post;
import com.survey.mole.service.worktracker.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private PostService postService;
    private PostMapper postMapper;

    @Autowired
    public PostController(PostService postService, PostMapper postMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
    }


    @GetMapping("/{id}")
    public PostDto findHolidayById(@PathVariable("id") Long id) {
        Post byId = postService.findById(id);
        return postMapper.toDto(byId);
    }

    @GetMapping("/list")
    public List<PostDto> findAll() {
        return postService.findAll().stream()
                .map(post -> postMapper.toDto(post))
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public PostDto addNewHoliday(@RequestBody PostDto postDto) {
        Post post = postMapper.toEntity(postDto);
        Post save = postService.save(post);
        return postMapper.toDto(save);
    }

    @PostMapping("/edit/{id}")
    public PostDto editBaseline(@PathVariable("id") Long id, @RequestBody PostDto postDto) {
        Post post = postMapper.toEntity(postDto);
        Post update = postService.update(id, post);
        return postMapper.toDto(update);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteBaseline(@PathVariable("id") Long id) {
        Post byId = postService.findById(id);
        return postService.delete(byId);
    }
}
