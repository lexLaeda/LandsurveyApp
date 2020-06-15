package com.survey.mole.mapper;

import com.survey.mole.dto.PointDto;
import com.survey.mole.model.survey.Point;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PointMapper extends AbstractMapper<Point, PointDto> {

    private ModelMapper modelMapper;

    @Autowired
    public PointMapper(ModelMapper modelMapper) {
        super(modelMapper, Point.class, PointDto.class);
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void initMapper() {
        modelMapper.createTypeMap(Point.class, PointDto.class)
                .setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(PointDto.class, Point.class)
                .setPostConverter(toEntityConverter());
    }
}
