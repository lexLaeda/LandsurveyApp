package com.survey.mole.service.worktracker;

import com.survey.mole.exception.ElementNotFoundException;
import com.survey.mole.model.worktracker.employee.Post;
import com.survey.mole.repository.worktracker.PostRepository;
import com.survey.mole.service.worktracker.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository repository;

    @Autowired
    public PostServiceImpl(PostRepository repository) {
        this.repository = repository;
    }


    @Override
    public Post save(Post post) {
        return repository.save(post);
    }

    @Override
    public Post update(Long id, Post post) {
        Post byId = findById(id);
        post.setId(id);
        return repository.saveAndFlush(post);
    }

    @Override
    public Post findById(Long id) {
        return repository.findById(id).orElseThrow(()->new ElementNotFoundException(String.valueOf(id)));
    }

    @Override
    public List<Post> findAll() {
        return repository.findAll();
    }

    @Override
    public Boolean delete(Post post) {
        long before = repository.count();
        repository.delete(post);
        long after = repository.count();
        return before - after == 1;
    }
}
