package com.survey.mole.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PairRequest {
    private PointDto pointDto;
    private LevelReferenceDto levelReferenceDto;
}
