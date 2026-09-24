package com.api.blog_api.service;

import com.api.blog_api.dto.request.ComentarioRequestDto;
import com.api.blog_api.dto.request.PostRequestDto;
import com.api.blog_api.dto.response.ComentarioResponseDto;
import com.api.blog_api.dto.response.PostResponseDto;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<PostResponseDto> findAll();
    PostResponseDto findById(UUID id);
    PostResponseDto createPost(PostRequestDto requestDtodto);
    ComentarioResponseDto addComentario(UUID postId, ComentarioRequestDto dto);
}
