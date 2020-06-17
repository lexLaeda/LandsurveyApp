package com.survey.mole.mapper;

import com.survey.mole.dto.PostDto;
import com.survey.mole.model.worktracker.employee.Post;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PostMapper extends AbstractMapper<Post, PostDto>{

    private ModelMapper modelmapper;

    @Autowired
    public PostMapper(ModelMapper modelMapper) {
        super(modelMapper, Post.class, PostDto.class);
        this.modelmapper = modelMapper;
    }

    @PostConstruct
    public void initMapper(){
        modelmapper.createTypeMap(Post.class,PostDto.class).setPostConverter(toDtoConverter());
        modelmapper.createTypeMap(PostDto.class,Post.class).setPostConverter(toEntityConverter());
    }
}
