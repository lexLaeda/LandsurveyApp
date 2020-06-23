package com.survey.mole.mapper;

import com.survey.mole.dto.PointDto;
import com.survey.mole.model.survey.LevelReference;
import com.survey.mole.model.survey.Point;
import com.survey.mole.service.LevelReferenceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Service
public class PointMapper extends AbstractMapper<Point, PointDto> {

    private ModelMapper modelMapper;
    private LevelReferenceService levelReferenceService;

    @Autowired
    public PointMapper(ModelMapper modelMapper, LevelReferenceService levelReferenceService) {
        super(modelMapper, Point.class, PointDto.class);
        this.modelMapper = modelMapper;
        this.levelReferenceService = levelReferenceService;
    }

    @PostConstruct
    public void initMapper() {
        modelMapper.createTypeMap(Point.class, PointDto.class)
                .setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(PointDto.class, Point.class)
                .setPostConverter(toEntityConverter());
    }


    @Override
    protected void mapSpecificFields(Point source, PointDto destination) {
        if(Objects.nonNull(source.getLevelReference())){
            destination.setLevelReferenceId(source.getLevelReference().getId());
        }
    }

    @Override
    protected void mapSpecificFields(PointDto source, Point destination) {
        if(Objects.nonNull(source.getLevelReferenceId())){
            LevelReference byId = levelReferenceService.findById(source.getLevelReferenceId());
            destination.setLevelReference(byId);
        }
    }
}
