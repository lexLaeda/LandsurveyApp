package com.survey.mole.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BaselineDto extends AbstractDto {
    private Long id;
    private String name;
    private PointDto pointStart;
    private PointDto pointEnd;
}
