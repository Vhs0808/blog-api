package com.api.blog_api.controller;

import com.api.blog_api.dto.request.ComentarioRequestDto;
import com.api.blog_api.dto.request.PostRequestDto;
import com.api.blog_api.dto.response.ComentarioResponseDto;
import com.api.blog_api.dto.response.PostResponseDto;
import com.api.blog_api.dto.response.PostSemComentarioResponseDto;
import com.api.blog_api.service.PostServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<Page<PostResponseDto>> getAllPosts(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "data") String sortBy,
            @RequestParam(defaultValue = "desc") String direction,
            @RequestParam(name = "titulo", required = false) String titulo
    )
    {
        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        return ResponseEntity.ok(service.findAllByTitulo(PageRequest.of(page,size,sort), titulo));
    }

    @Operation(summary = "Lista post com paginação e sem os comentários")
    @GetMapping("/posts/noComents")
    public ResponseEntity<Page<PostSemComentarioResponseDto>> getAllPostsWithoutCComents(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "data") String sortBy,
            @RequestParam(defaultValue = "desc") String direction,
            @RequestParam(name = "titulo", required = false) String titulo
    )
    {
        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        return ResponseEntity.ok(service.findAllPostsWithoutComentsByTitulo
                (PageRequest.of(page,size,sort), titulo));
    }

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


    @Operation(summary = "Edita um post")
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostResponseDto> editPost(
            @PathVariable UUID postId,
            @RequestBody PostRequestDto req
    ){

        return ResponseEntity.ok(service.updatePost(postId, req));
    }

    @Operation(summary = "Deleta um post")
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable UUID postId){
            service.deletePost(postId);
            return ResponseEntity.noContent().build();
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

    @Operation(summary = "Edita um comentário")
    @PatchMapping("/comentarios/{comentarioId}")
    public ResponseEntity<ComentarioResponseDto> updateComentario(
            @PathVariable UUID comentarioId,
            @RequestBody ComentarioRequestDto comentarioRequestDto
    ){
        return ResponseEntity.ok(service.updateComentario(comentarioId, comentarioRequestDto));
    }

    @Operation(summary = "Deleta um comentário")
    @DeleteMapping("/comentarios/{comentarioId}")
    public ResponseEntity<Void> deleteComentario(@PathVariable UUID comentarioId){
        service.deleteComentario(comentarioId);
        return ResponseEntity.noContent().build();
    }
}
