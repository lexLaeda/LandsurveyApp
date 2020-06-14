package com.survey.mole.mapper;

import com.survey.mole.dto.BaselineDto;
import com.survey.mole.model.survey.Baseline;
import com.survey.mole.model.survey.Point;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BaselineMapper extends AbstractMapper<Baseline, BaselineDto> {

    private ModelMapper modelMapper;

    private PointMapper pointMapper;

    @Autowired
    public BaselineMapper(ModelMapper modelMapper, PointMapper pointMapper) {
        super(modelMapper, Baseline.class, BaselineDto.class);
        this.modelMapper = modelMapper;
        this.pointMapper = pointMapper;
    }

    @PostConstruct
    public void initMapper() {
        modelMapper.createTypeMap(Baseline.class, BaselineDto.class)
                .setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(BaselineDto.class, Baseline.class)
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(Baseline source, BaselineDto destination) {
            destination.setPointStart(pointMapper.toDto(source.getPoints().get(0)));
            destination.setPointEnd(pointMapper.toDto(source.getPoints().get(1)));
    }

    @Override
    protected void mapSpecificFields(BaselineDto source, Baseline destination) {
        List<Point> points = new ArrayList<>();
        points.add(pointMapper.toEntity(source.getPointStart()));
        points.add(pointMapper.toEntity(source.getPointEnd()));
        destination.setPoints(points);
    }
}
