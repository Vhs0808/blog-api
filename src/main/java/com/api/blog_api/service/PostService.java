package com.api.blog_api.service;

import com.api.blog_api.dto.request.ComentarioRequestDto;
import com.api.blog_api.dto.request.PostRequestDto;
import com.api.blog_api.dto.response.ComentarioResponseDto;
import com.api.blog_api.dto.response.PostResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PostService {
    Page<PostResponseDto> findAll(Pageable pageable);
    Page<PostResponseDto> findByTitulo(Pageable pageable, String titulo);
    PostResponseDto findById(UUID id);
    PostResponseDto createPost(PostRequestDto requestDtodto);
    ComentarioResponseDto createComentario(UUID postId, ComentarioRequestDto dto);
}
