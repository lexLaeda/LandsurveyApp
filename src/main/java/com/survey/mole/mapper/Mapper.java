package com.survey.mole.mapper;

import com.survey.mole.dto.AbstractDto;
import com.survey.mole.model.AbstractEntity;

public interface Mapper <E extends AbstractEntity, D extends AbstractDto>{
    E toEntity(D dto);
    D toDto(E entity);
}
