package com.survey.mole.service.worktracker;

import com.survey.mole.exception.ElementNotFoundException;
import com.survey.mole.model.worktracker.Code;
import com.survey.mole.repository.worktracker.CodeRepository;

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

class CodeServiceImplTest {

    @Mock
    private CodeRepository repository;

    @InjectMocks
    private CodeServiceImpl codeService;

    private static Code one = new Code(1L, "one", "description one");
    private static Code two = new Code(2L, "two", "description two");
    private static Code three = new Code(3L, "three", "description three");

    private static List<Code> codeList = new ArrayList<>();

    static {
        codeList.add(one);
        codeList.add(two);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void save() {
        Mockito.when(repository.saveAndFlush(three)).thenReturn(three);
        assertEquals(three, codeService.save(three));
    }

    @Test
    void update() {
        Mockito.when(repository.findById(3L)).thenReturn(Optional.of(three));
        Mockito.when(repository.saveAndFlush(three)).thenReturn(three);
        assertEquals(three, codeService.update(3L, three));
    }

    @Test
    void findById() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(one));

        assertEquals(one, codeService.findById(1L));
    }

    @Test
    void findByFakeId() {
        Mockito.when(repository.findById(4L)).thenReturn(Optional.ofNullable(null));

        ElementNotFoundException elementNotFoundException = assertThrows(ElementNotFoundException.class, () -> codeService.findById(4L));

        assertTrue(elementNotFoundException.getMessage().contains("Code"));

        assertTrue(elementNotFoundException.getMessage().contains("4"));
    }

    @Test
    void findAll() {
        Mockito.when(repository.findAll()).thenReturn(codeList);

        assertEquals(codeList, codeService.findAll());
    }

    @Test
    void delete() {
        Mockito.when(repository.count()).thenReturn(2L);
        Mockito.doAnswer(invocation -> {
            Code code = invocation.getArgument(0);
            code.setId(-1L);
            return null;
        }).when(repository).delete(two);

        assertFalse(codeService.delete(two));
    }
}