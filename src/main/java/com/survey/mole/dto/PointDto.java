package com.survey.mole.dto;

import com.survey.mole.model.survey.LevelReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PointDto extends AbstractDto {

    private Long id;
    private String name;
    private Double x;
    private Double y;
    private Double h;
    private Long levelReferenceId;
}
