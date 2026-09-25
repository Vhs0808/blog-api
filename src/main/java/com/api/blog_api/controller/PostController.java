package com.api.blog_api.controller;

import com.api.blog_api.dto.request.ComentarioRequestDto;
import com.api.blog_api.dto.request.PostRequestDto;
import com.api.blog_api.dto.response.ComentarioResponseDto;
import com.api.blog_api.dto.response.PostResponseDto;
import com.api.blog_api.service.PostServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostServiceImpl service;

    public PostController(PostServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponseDto>> getAllPosts(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable UUID id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("/newpost")
    public ResponseEntity<PostResponseDto> createPost(
            @RequestBody @Valid PostRequestDto req,
            UriComponentsBuilder uriBuilder
            ){
        PostResponseDto created = service.createPost(req);
        URI uri = uriBuilder.path("/newpost").buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @PostMapping("/comentarios/{postId}")
    public ResponseEntity<ComentarioResponseDto> createComentario(
            @PathVariable UUID postId,
            @RequestBody @Valid ComentarioRequestDto req,
            UriComponentsBuilder uriBuilder
            ){
        ComentarioResponseDto created = service.createComentario(postId, req);
        URI uri = uriBuilder.path("//comentarios/{postId}").buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(uri).body(created);

    }
}
