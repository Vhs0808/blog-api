package com.api.blog_api.mapper;

import com.api.blog_api.dto.request.PostRequestDto;
import com.api.blog_api.dto.response.PostResponseDto;
import com.api.blog_api.entity.PostEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PostMapper {

    public PostEntity toEntity(PostRequestDto dto){
        if(dto == null){
            return null;
        }
        return new PostEntity(
                dto.autor(),
                dto.titulo(),
                dto.texto(),
                LocalDate.now()
        );
    }

    public PostResponseDto toDto(PostEntity entity){
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
