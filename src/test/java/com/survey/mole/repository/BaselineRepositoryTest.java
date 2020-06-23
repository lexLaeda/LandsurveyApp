package com.survey.mole.repository;

import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@SpringBootTest
@TestMethodOrder(value = OrderAnnotation.class)
class BaselineRepositoryTest {


    @Autowired
    private BaselineRepository repository;


}