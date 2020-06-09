package com.survey.mole.mapper;

import com.survey.mole.dto.BaselineDto;
import com.survey.mole.model.survey.Baseline;
import com.survey.mole.model.survey.Point;
import com.survey.mole.service.PointService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

@Service
public class BaselineMapper extends AbstractMapper<Baseline, BaselineDto> {

    private ModelMapper modelMapper;

    private PointService pointService;

    @Autowired
    public BaselineMapper(ModelMapper modelMapper, PointService pointService) {
        super(modelMapper, Baseline.class, BaselineDto.class);
        this.modelMapper = modelMapper;
        this.pointService = pointService;
    }

    @PostConstruct
    public void initMapper(){
        modelMapper.createTypeMap(Baseline.class,BaselineDto.class)
                .setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(BaselineDto.class,Baseline.class)
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(Baseline source, BaselineDto destination) {
        destination.setPoints(source.getPoints().stream()
                .map(Point::getId)
                .collect(Collectors.toList()));
    }

    @Override
    protected void mapSpecificFields(BaselineDto source, Baseline destination) {
        destination.setPoints(source.getPoints().stream()
                .map(id -> pointService.findById(id))
                .collect(Collectors.toList()));
    }
}
