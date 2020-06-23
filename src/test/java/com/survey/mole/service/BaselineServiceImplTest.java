package com.survey.mole.service;

import com.survey.mole.exception.ElementNotFoundException;
import com.survey.mole.model.survey.Baseline;
import com.survey.mole.model.survey.Point;
import com.survey.mole.repository.BaselineRepository;

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


class BaselineServiceImplTest {

    @Mock
    private BaselineRepository repository;

    @InjectMocks
    private BaselineServiceImpl baselineService;

    private static List<Point> points = new ArrayList<>();
    private static List<Baseline> baselines = new ArrayList<>();

    private static Baseline one = new Baseline(1L, "one", points);
    private static Baseline two = new Baseline(2L, "two", points);
    private static Baseline three = new Baseline(3L, "three", points);

    static {
        points.add(new Point());
        points.add(new Point());
        baselines.add(one);
        baselines.add(two);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void save() {
        Mockito.when(repository.saveAndFlush(three)).thenReturn(three);
        assertEquals(three, baselineService.save(three));
    }

    @Test
    void update() {
        three.setName("updated");
        Mockito.when(repository.saveAndFlush(three)).thenReturn(three);
        Mockito.when(repository.findById(3L)).thenReturn(Optional.of(three));

        assertEquals(three, baselineService.update(3L, three));
    }

    @Test
    void findById() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(one));

        assertEquals(one, baselineService.findById(1L));
    }

    @Test
    void findByFakeId() {
        Mockito.when(repository.findById(4L)).thenReturn(Optional.ofNullable(null));

        ElementNotFoundException elementNotFoundException = assertThrows(ElementNotFoundException.class, () -> baselineService.findById(4L));

        assertTrue(elementNotFoundException.getMessage().contains("Baseline"));
        assertTrue(elementNotFoundException.getMessage().contains("4"));
    }

    @Test
    void findAll() {

        Mockito.when(repository.findAll()).thenReturn(baselines);
        assertEquals(baselines, baselineService.findAll());
    }

    @Test
    void delete() {
        Mockito.when(repository.count()).thenReturn(2L);
        Mockito.doAnswer(invocation -> {
            Baseline baseline = invocation.getArgument(0);
            baseline.setId(-1L);
            return null;
        }).when(repository).delete(two);

        assertFalse(baselineService.delete(two));
    }
}