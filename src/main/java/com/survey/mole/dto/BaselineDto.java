package com.survey.mole.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class BaselineDto extends AbstractDto{
    private Long id;
    private String name;
    private PointDto pointStart;
    private PointDto pointEnd;
}
