package com.survey.mole.controller;

import com.survey.mole.model.worktracker.employee.Post;
import com.survey.mole.service.worktracker.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("?api/post")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("/{id}")
    public Post findHolidayById(@PathVariable("id") Long id) {
        Post byId = postService.findById(id);
        return byId;
    }

    @GetMapping("/list")
    public List<Post> findAll() {
        return postService.findAll();
    }

    @PostMapping("/add")
    public Post addNewHoliday(@RequestBody Post post) {
        return postService.save(post);
    }

    @PostMapping("/edit/{id}")
    public Post editBaseline(@PathVariable("id") Long id, @RequestBody Post post) {
        return postService.update(id, post);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteBaseline(@PathVariable("id") Long id) {
        Post byId = postService.findById(id);
        return postService.delete(byId);
    }
}
