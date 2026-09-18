package com.api.blog_api.mapper;

import com.api.blog_api.dto.request.PostRequestDto;
import com.api.blog_api.dto.response.PostResponseDto;
import com.api.blog_api.entity.PostModel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PostMapper {

    public PostModel toEntity(PostRequestDto dto){
        if(dto == null){
            return null;
        }
        return new PostModel(
                dto.autor(),
                dto.titulo(),
                LocalDate.now(),
                dto.texto()
        );
    }

    public PostResponseDto toDto(PostModel entity){
        if(entity == null){
            return null;
        }
        return new PostResponseDto(
                entity.getId(),
                entity.getAutor(),
                entity.getData(),
                entity.getTitulo(),
                entity.getTexto()
        );
    }
}
