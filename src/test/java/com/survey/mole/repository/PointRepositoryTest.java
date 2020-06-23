package com.survey.mole.repository;

import com.survey.mole.model.survey.Point;
import com.survey.mole.model.worktracker.Department;
import com.survey.mole.model.worktracker.employee.Post;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(value = OrderAnnotation.class)
class PointRepositoryTest {

    @Autowired
    private PointRepository pointRepository;

    private static Point one = new Point();
    private static Point two = new Point(11L, "one", 2.0, 2.0, 2.0);
    private static Point three = new Point();

    static {
        one.setName("one");
        one.setX(1.0);
        one.setY(1.0);
        one.setH(1.0);
    }


    @Test
    @Order(1)
    void savePoint() {
        Point point = pointRepository.saveAndFlush(one);
        one.setId(1L);
        assertEquals(one,point);
    }

    @Test
    @Order(2)
    void findById() {
        Optional<Point> byId = pointRepository.findById(1L);
        assertEquals(one,byId.get());
    }

    @Test
    @Order(3)
    void findByFakeId() {
        Optional<Point> byId = pointRepository.findById(1000L);
        assertFalse(byId.isPresent());
    }

    @Test
    @Order(4)
    void findAll() {
        List<Point> points = new ArrayList<>();
        pointRepository.saveAndFlush(two);
        two.setId(2L);
        points.add(one);
        points.add(two);
        assertEquals(points,pointRepository.findAll());
    }

    @Test
    @Order(5)
    void delete() {
        List<Point> points = new ArrayList<>();
        pointRepository.delete(two);
        points.add(one);
        assertEquals(points,pointRepository.findAll());
    }
}