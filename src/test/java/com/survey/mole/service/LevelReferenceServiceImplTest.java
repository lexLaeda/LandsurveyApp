package com.survey.mole.service;

import com.survey.mole.exception.ElementNotFoundException;
import com.survey.mole.model.survey.LevelReference;
import com.survey.mole.model.worktracker.Holiday;
import com.survey.mole.repository.LevelReferenceRepository;
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


class LevelReferenceServiceImplTest {

    @Mock
    private LevelReferenceRepository repository;

    @InjectMocks
    private LevelReferenceServiceImpl levelReferenceService;

    private static List<LevelReference> LRlist = new ArrayList<>();

    private static LevelReference one = new LevelReference(1L, "one", 30.453);

    private static LevelReference two = new LevelReference(2L, "two", 30.345);

    private static LevelReference three = new LevelReference(2L, "three", 30.222);

    static {
        LRlist.add(one);
        LRlist.add(two);
    }


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void save() {
        Mockito.when(repository.saveAndFlush(three)).thenReturn(three);

        assertEquals(three, levelReferenceService.save(three));
    }

    @Test
    void update() {
        three.setName("update");
        Mockito.when(repository.findById(3L)).thenReturn(Optional.of(three));
        Mockito.when(repository.saveAndFlush(three)).thenReturn(three);
        assertEquals(three,levelReferenceService.update(3L,three));
    }

    @Test
    void findById() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(one));
        assertEquals(one,levelReferenceService.findById(1L));
    }

    @Test
    void findByFakeId() {
        Mockito.when(repository.findById(4L)).thenReturn(Optional.ofNullable(null));
        ElementNotFoundException elementNotFoundException = assertThrows(ElementNotFoundException.class, () -> levelReferenceService.findById(4L));
        assertTrue(elementNotFoundException.getMessage().contains("LevelReference"));
        assertTrue(elementNotFoundException.getMessage().contains("4"));
    }

    @Test
    void findAll() {
        Mockito.when(repository.findAll()).thenReturn(LRlist);
        assertEquals(LRlist,levelReferenceService.findAll());
    }

    @Test
    void delete() {
        Mockito.when(repository.count()).thenReturn(2L);
        Mockito.doAnswer(invocation -> {
            LevelReference levelReference = invocation.getArgument(0);
            levelReference.setId(-1L);
            return null;
        }).when(repository).delete(two);

        Assert.assertFalse(levelReferenceService.delete(two));
    }
}