package com.survey.mole.service.worktracker;

import com.survey.mole.exception.ElementNotFoundException;
import com.survey.mole.model.worktracker.employee.Post;
import com.survey.mole.repository.worktracker.PostRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PostServiceImplTest {

    @Mock
    private PostRepository repository;

    @InjectMocks
    private PostServiceImpl postService;

    private static Post one = new Post(1L,"one",new ArrayList<>());
    private static Post two = new Post(2L,"two",new ArrayList<>());
    private static Post three = new Post(3L,"three",new ArrayList<>());

    private static List<Post> posts = new ArrayList<>();

    static {
        posts.add(one);
        posts.add(two);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void save() {
        Mockito.when(repository.saveAndFlush(three)).thenReturn(three);

        assertEquals(three,postService.save(three));
    }

    @Test
    void update() {
        two.setName("update");
        Mockito.when(repository.saveAndFlush(two)).thenReturn(two);
        Mockito.when(repository.findById(2L)).thenReturn(Optional.of(two));

        assertEquals(two,postService.update(2L,two));
    }

    @Test
    void findById() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(one));
        assertEquals(one,postService.findById(1L));
    }

    @Test
    void findByFakeId() {
        Mockito.when(repository.findById(4L)).thenReturn(Optional.ofNullable(null));
        ElementNotFoundException elementNotFoundException = assertThrows(ElementNotFoundException.class, () -> postService.findById(4L));

        assertTrue(elementNotFoundException.getMessage().contains("Post"));
        assertTrue(elementNotFoundException.getMessage().contains("4"));

    }


    @Test
    void findAll() {
        Mockito.when(repository.findAll()).thenReturn(posts);
        assertEquals(posts,postService.findAll());
    }

    @Test
    void delete() {
        Mockito.doAnswer(invocation -> {
            Post post = invocation.getArgument(0);
            post.setId(-1L);
            return null;
        }).when(repository).delete(two);
        Assert.assertFalse(postService.delete(two));
    }
}