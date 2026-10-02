package com.api.blog_api.controller;

import com.api.blog_api.dto.request.ComentarioRequestDto;
import com.api.blog_api.dto.request.PostRequestDto;
import com.api.blog_api.dto.response.ComentarioResponseDto;
import com.api.blog_api.dto.response.PostResponseDto;
import com.api.blog_api.service.PostServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Tag(name="Posts e comentários", description = "Operações de Blog API")
@RestController
@RequestMapping("/api")
public class PostController {

    private final PostServiceImpl service;

    public PostController(PostServiceImpl service) {
        this.service = service;
    }

    @Operation(summary = "Lista post com paginação")
    @GetMapping("/posts")
    public ResponseEntity<List<PostResponseDto>> getAllPosts(){return ResponseEntity.ok(service.findAll());}

    @Operation(summary = "Busca um post pelo ID")
    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable UUID id){
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Cria um post novo")
    @PostMapping("/newpost")
    public ResponseEntity<PostResponseDto> createPost(
            @RequestBody @Valid PostRequestDto req,
            UriComponentsBuilder uriBuilder
            ){
        PostResponseDto created = service.createPost(req);
        URI uri = uriBuilder.path("/newpost").buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @Operation(summary = "Cria um comentário novo de um post")
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
