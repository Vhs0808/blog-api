package com.api.blog_api.service;

import com.api.blog_api.dto.request.PostRequestDto;
import com.api.blog_api.dto.response.PostResponseDto;
import com.api.blog_api.entity.PostModel;
import com.api.blog_api.mapper.PostMapper;
import com.api.blog_api.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    @Transactional(readOnly = true)
    public List<PostResponseDto> findAll(){
        List<PostModel> posts = postRepository.findAll();
        List<PostResponseDto> dtos = new ArrayList<>();

        for(PostModel post : posts){
            dtos.add(postMapper.toDto(post));
        }

        return dtos;
    }

    @Transactional(readOnly = true)
    public PostResponseDto findById(UUID id) {
        Optional<PostModel> optionalPost = postRepository.findById(id);

        if(optionalPost.isEmpty()){
            throw new RuntimeException("Post não encontrado com o ID: " + id);
        }

        PostModel post = optionalPost.get();
        return postMapper.toDto(post);
    }

    @Override
    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDtodto) {
        PostModel post = postMapper.toEntity(requestDtodto);
        PostModel saved = postRepository.save(post);
        return postMapper.toDto(saved);
    }


}
