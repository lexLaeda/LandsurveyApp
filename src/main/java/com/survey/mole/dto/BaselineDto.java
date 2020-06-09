package com.survey.mole.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BaselineDto extends AbstractDto{

    private Long id;

    private String name;

    private List<Long> points = new ArrayList<>();
}
