package com.survey.mole.mapper;

import com.survey.mole.dto.AbstractDto;
import com.survey.mole.model.AbstractEntity;

public class AbstractMapper <E extends AbstractEntity, D extends AbstractDto> implements Mapper<E,D> {

    public  AbstractMapper(Class<E> entityClass, Class<D> dtoClass){

    }

    @Override
    public E toEntity(D dto) {
        return null;
    }

    @Override
    public D toDto(E entity) {
        return null;
    }
}
