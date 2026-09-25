package com.api.blog_api.mapper;

import com.api.blog_api.dto.request.PostRequestDto;
import com.api.blog_api.dto.response.ComentarioResponseDto;
import com.api.blog_api.dto.response.PostResponseDto;
import com.api.blog_api.entity.ComentarioEntity;
import com.api.blog_api.entity.PostEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PostMapper {

    private final ComentarioMapper comentarioMapper;

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

        List<ComentarioResponseDto> comentariosDto = new ArrayList<>();

        if(entity.getComentarios() != null){
            for(ComentarioEntity comentario : entity.getComentarios()){
                ComentarioResponseDto dto = comentarioMapper.toDto(comentario);
                comentariosDto.add(dto);
            }
        }

        return new PostResponseDto(
                entity.getId(),
                entity.getAutor(),
                entity.getData(),
                entity.getTitulo(),
                entity.getTexto(),
                comentariosDto
        );
    }
}
