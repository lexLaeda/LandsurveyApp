package com.survey.mole.service.worktracker;

import com.survey.mole.exception.ElementNotFoundException;
import com.survey.mole.model.worktracker.Holiday;
import com.survey.mole.repository.worktracker.HolidayRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class HolidayServiceImplTest {

    @Mock
    private HolidayRepository repository;

    @InjectMocks
    private HolidayServiceImpl holidayService;

    private static Holiday one = new Holiday(1L, "one", LocalDate.of(2020, 1, 1));
    private static Holiday two = new Holiday(1L, "two", LocalDate.of(2020, 2, 2));
    private static Holiday three = new Holiday(3L, "three", LocalDate.of(2020, 3, 3));

    private static List<Holiday> holidayList = new ArrayList<>();

    static {
        holidayList.add(one);
        holidayList.add(two);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void save() {
        Mockito.when(repository.saveAndFlush(one)).thenReturn(one);

        assertEquals(one, holidayService.save(one));
    }

    @Test
    void update() {
        two.setName("update");
        Mockito.when(repository.saveAndFlush(two)).thenReturn(two);
        Mockito.when(repository.findById(2L)).thenReturn(Optional.of(two));

        assertEquals(two, holidayService.update(2L, two));
    }

    @Test
    void findById() {
        Mockito.when(repository.findById(2L)).thenReturn(Optional.of(two));

        assertEquals(two, holidayService.findById(2L));
    }

    @Test
    void findByFakeId() {
        Mockito.when(repository.findById(4L)).thenReturn(Optional.ofNullable(null));
        ElementNotFoundException elementNotFoundException = assertThrows(ElementNotFoundException.class, () -> holidayService.findById(4L));
        assertTrue(elementNotFoundException.getMessage().contains("Holiday"));
        assertTrue(elementNotFoundException.getMessage().contains("4"));
    }

    @Test
    void findAll() {
        Mockito.when(repository.findAll()).thenReturn(holidayList);

        assertEquals(holidayList, holidayService.findAll());
    }

    @Test
    void delete() {
        Mockito.when(repository.count()).thenReturn(2L);
        Mockito.doAnswer(invocation -> {
            Holiday holiday = invocation.getArgument(0);
            holiday.setId(-1L);
            return null;
        }).when(repository).delete(two);

        assertFalse(holidayService.delete(two));
    }


}