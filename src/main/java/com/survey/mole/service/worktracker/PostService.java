package com.survey.mole.service.worktracker;

import com.survey.mole.model.worktracker.employee.Post;

import java.util.List;

public interface PostService {

    Post save(Post post);

    Post update(Long id, Post post);

    Post findById(Long id);

    List<Post> findAll();

    Boolean delete(Post post);
}
