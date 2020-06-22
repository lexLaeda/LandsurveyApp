package com.survey.mole.service;

import com.survey.mole.model.survey.Point;
import com.survey.mole.repository.PointRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class PointServiceImplTest {

    @Mock
    private PointRepository pointRepository;

    @InjectMocks
    private PointServiceImpl pointService;

    private static List<Point> points  = new ArrayList<>();

    static {
        Point one = new Point(1L,"one",1.0,1.0,1.0);
        Point two = new Point(1L,"one",1.0,1.0,1.0);
        points.add(one);
        points.add(two);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSave() {
        Mockito.when(pointRepository.findAll()).thenReturn(points);
    }

    @Test
    void update() {

    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void delete() {
    }
}