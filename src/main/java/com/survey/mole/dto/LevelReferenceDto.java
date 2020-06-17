package com.survey.mole.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LevelReferenceDto extends AbstractDto {
    private Long id;
    private String name;
    private Double elevation;
}
