package com.survey.mole.repository;

import com.survey.mole.model.survey.LevelReference;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@SpringBootTest
@TestMethodOrder(value = OrderAnnotation.class)
class LevelReferenceRepositoryTest {

    @Autowired
    private LevelReferenceRepository levelReferenceRepository;

    private static List<LevelReference> LRlist = new ArrayList<>();

    private static LevelReference one = new LevelReference();

    private static LevelReference two = new LevelReference();


    static {
        one.setName("one");
        one.setElevation(30.451);

        two.setName("two");
        two.setElevation(43.999);
    }

    @Test
    @Order(1)
    void savePoint() {
        LevelReference levelReference = levelReferenceRepository.saveAndFlush(one);

        assertEquals(one, levelReference);
    }

    @Test
    @Order(2)
    void findById() {

        assertEquals(one.getName(), levelReferenceRepository.findById(1L).get().getName());

    }

    @Test
    @Order(3)
    void findByFakeId() {
        Optional<LevelReference> byId = levelReferenceRepository.findById(1000L);

        assertFalse(byId.isPresent());
    }

    @Test
    @Order(4)
    void findAll() {
        List<LevelReference> lrList = new ArrayList<>();
        lrList.add(one);
        lrList.add(levelReferenceRepository.saveAndFlush(two));
        assertEquals(lrList, levelReferenceRepository.findAll());
    }

    @Test
    @Order(5)
    void delete() {
        List<LevelReference> lrList = new ArrayList<>();
        lrList.add(one);
        two.setId(2L);
        levelReferenceRepository.delete(two);

        assertEquals(lrList, levelReferenceRepository.findAll());
    }
}