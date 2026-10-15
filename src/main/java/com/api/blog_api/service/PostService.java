package com.api.blog_api.service;

import com.api.blog_api.dto.request.ComentarioRequestDto;
import com.api.blog_api.dto.request.PostRequestDto;
import com.api.blog_api.dto.response.ComentarioResponseDto;
import com.api.blog_api.dto.response.PostResponseDto;
import com.api.blog_api.dto.response.PostSemComentarioResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PostService {
    void deletePost(UUID postId);
    PostResponseDto updatePost(UUID postId, PostRequestDto req);
    Page<PostResponseDto> findAllByTitulo(Pageable pageable, String titulo);
    Page<PostSemComentarioResponseDto> findAllPostsWithoutComentsByTitulo(Pageable pageable, String titulo);
    PostResponseDto findById(UUID id);
    PostResponseDto createPost(PostRequestDto requestDtodto);
    ComentarioResponseDto createComentario(UUID postId, ComentarioRequestDto dto);
    ComentarioResponseDto updateComentario(UUID comentarioId, ComentarioRequestDto dto);
    void deleteComentario(UUID comentarioId);
}
