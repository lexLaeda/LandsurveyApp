package com.survey.mole.mapper;

import com.survey.mole.dto.LevelReferenceDto;
import com.survey.mole.model.survey.LevelReference;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class LevelReferenceMapper extends AbstractMapper<LevelReference, LevelReferenceDto> {

    private ModelMapper modelMapper;

    @Autowired
    public LevelReferenceMapper(ModelMapper modelMapper) {
        super(modelMapper, LevelReference.class, LevelReferenceDto.class);
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void initMapper() {
        modelMapper.createTypeMap(LevelReference.class, LevelReferenceDto.class)
                .setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(LevelReferenceDto.class, LevelReference.class)
                .setPostConverter(toEntityConverter());
    }
}
