package com.api.blog_api.mapper;

import com.api.blog_api.dto.request.ComentarioRequestDto;
import com.api.blog_api.dto.response.ComentarioResponseDto;
import com.api.blog_api.entity.ComentarioEntity;
import com.api.blog_api.entity.PostEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ComentarioMapper {

    public ComentarioEntity toEntity(ComentarioRequestDto req, PostEntity post){
        if(req == null){
            return null;
        }

        return new ComentarioEntity(
                LocalDate.now(),
                req.comentario(),
                post
        );
    }

    public ComentarioResponseDto toDto(ComentarioEntity entity){
        if(entity == null){
            return null;
        }

        return new ComentarioResponseDto(
                entity.getId(),
                entity.getData(),
                entity.getComentario()
        );
    }

}
