package com.survey.mole.dto;

import lombok.Data;

@Data
public class LevelReferenceDto extends AbstractDto{
    private Long id;
    private String name;
    private Double elevation;
}
