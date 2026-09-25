package com.api.blog_api.service;

import com.api.blog_api.dto.request.ComentarioRequestDto;
import com.api.blog_api.dto.request.PostRequestDto;
import com.api.blog_api.dto.response.ComentarioResponseDto;
import com.api.blog_api.dto.response.PostResponseDto;
import com.api.blog_api.entity.ComentarioEntity;
import com.api.blog_api.entity.PostEntity;
import com.api.blog_api.mapper.ComentarioMapper;
import com.api.blog_api.mapper.PostMapper;
import com.api.blog_api.repository.ComentarioRepository;
import com.api.blog_api.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final ComentarioRepository comentarioRepository;
    private final ComentarioMapper comentarioMapper;

    @Override
    @Transactional(readOnly = true)
    public List<PostResponseDto> findAll(){
        List<PostEntity> posts = postRepository.findAll();
        List<PostResponseDto> dtos = new ArrayList<>();

        for(PostEntity post : posts){
            dtos.add(postMapper.toDto(post));
        }

        return dtos;
    }

    @Transactional(readOnly = true)
    public PostResponseDto findById(UUID id) {
        Optional<PostEntity> optionalPost = postRepository.findById(id);

        if(optionalPost.isEmpty()){
            throw new RuntimeException("Post não encontrado com o ID: " + id);
        }

        PostEntity post = optionalPost.get();
        return postMapper.toDto(post);
    }

    @Override
    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDtodto) {
        PostEntity post = postMapper.toEntity(requestDtodto);
        PostEntity saved = postRepository.save(post);
        return postMapper.toDto(saved);
    }

    @Override
    @Transactional
    public ComentarioResponseDto createComentario(UUID postId, ComentarioRequestDto dto) {
        Optional<PostEntity> optionalPost = postRepository.findById(postId);
        PostEntity post = optionalPost.get();

        ComentarioEntity comentario = new ComentarioEntity(dto.comentario(), post);

        post.adicionarComentario(comentario);
        ComentarioEntity saved = comentarioRepository.save(comentario);

        return comentarioMapper.toDto(saved);
    }


}
