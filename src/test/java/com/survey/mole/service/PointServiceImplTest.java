package com.survey.mole.service;

import com.survey.mole.exception.ElementNotFoundException;
import com.survey.mole.model.survey.Point;
import com.survey.mole.repository.PointRepository;

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


class PointServiceImplTest {

    @Mock
    private PointRepository pointRepository;

    @InjectMocks
    private PointServiceImpl pointService;

    private static List<Point> points = new ArrayList<>();
    private static Point one = new Point();
    private static Point two = new Point();

    static {
        points.add(one);
        points.add(two);
        one.setId(1L);
        one.setName("one");
        one.setX(1.0);
        one.setY(1.0);
        one.setH(1.0);

        two.setId(2L);
        two.setName("two");
        two.setX(2.0);
        two.setY(2.0);
        two.setH(2.0);


    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSave() {
        Point three = new Point();
        three.setId(3L);
        three.setName("three");
        three.setX(3.0);
        three.setY(3.0);
        three.setH(3.0);
        Mockito.when(pointRepository.saveAndFlush(three)).thenReturn(three);
        assertEquals(three, pointService.save(three));
    }

    @Test
    void update() {
        Mockito.when(pointRepository.saveAndFlush(two)).thenReturn(two);
        Mockito.when(pointRepository.findById(2L)).thenReturn(Optional.of(two));
        assertEquals(two, pointService.update(2L, two));
    }

    @Test
    void findById() {
        Mockito.when(pointRepository.findById(1L)).thenReturn(Optional.of(one));
        assertEquals(one, pointService.findById(1L));
    }

    @Test
    void findByFakeId() {
        Mockito.when(pointRepository.findById(3L)).thenReturn(Optional.ofNullable(null));
        ElementNotFoundException elementNotFoundException = assertThrows(ElementNotFoundException.class, () -> pointService.findById(3L));
        assertTrue(elementNotFoundException.getMessage().contains("3"));
        assertTrue(elementNotFoundException.getMessage().contains("Point"));
    }

    @Test
    void findAll() {
        Mockito.when(pointRepository.findAll()).thenReturn(points);
        assertEquals(points, pointService.findAll());
    }

    @Test
    void delete() {
        Mockito.when(pointRepository.count()).thenReturn(2L);
        Mockito.doAnswer(invocation -> {
            Point point = invocation.getArgument(0);
            point.setId(-1L);
            return null;
        }).when(pointRepository).delete(two);

        assertFalse(pointService.delete(two));
    }

}