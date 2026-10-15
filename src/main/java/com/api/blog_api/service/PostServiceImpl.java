package com.api.blog_api.service;

import com.api.blog_api.dto.request.ComentarioRequestDto;
import com.api.blog_api.dto.request.PostRequestDto;
import com.api.blog_api.dto.response.ComentarioResponseDto;
import com.api.blog_api.dto.response.PostResponseDto;
import com.api.blog_api.dto.response.PostSemComentarioResponseDto;
import com.api.blog_api.entity.ComentarioEntity;
import com.api.blog_api.entity.PostEntity;
import com.api.blog_api.mapper.ComentarioMapper;
import com.api.blog_api.mapper.PostMapper;
import com.api.blog_api.repository.ComentarioRepository;
import com.api.blog_api.repository.PostRepository;
import com.api.blog_api.exception.RecursoNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final ComentarioRepository comentarioRepository;
    private final ComentarioMapper comentarioMapper;
    private final MessageService messageService;

    @Override
    @Transactional(readOnly = true)
    public Page<PostResponseDto> findAllByTitulo(Pageable pageable, String titulo){
        Page<PostEntity> posts;

        if (titulo == null || titulo.isBlank())
            posts = postRepository.findAll(pageable);
        else posts = postRepository.findByTituloContainingIgnoreCase(titulo, pageable);

        return posts.map(postMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PostSemComentarioResponseDto> findAllPostsWithoutComentsByTitulo(Pageable pageable, String titulo){
        Page<PostEntity> posts;

        if (titulo == null || titulo.isBlank())
            posts = postRepository.findAll(pageable);
        else posts = postRepository.findByTituloContainingIgnoreCase(titulo, pageable);

        return posts.map(postMapper::toDtoWithoutComents);
    }

    @Transactional(readOnly = true)
    public PostResponseDto findById(UUID id) {
        PostEntity post = postRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        messageService.getMessage("post.nao.encontrado", id)));
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
    public PostResponseDto updatePost(UUID postId, PostRequestDto req) {
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        messageService.getMessage("post.nao.encontrado", postId)));

        post.setAutor(req.autor());
        post.setTitulo(req.titulo());
        post.setTexto(req.texto());

        PostEntity updated = postRepository.save(post);
        return postMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void deletePost(UUID postId){
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        messageService.getMessage("post.nao.encontrado", postId)));
        postRepository.deleteById(postId);
    }

    @Override
    @Transactional
    public ComentarioResponseDto createComentario(UUID postId, ComentarioRequestDto dto) {
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        messageService.getMessage("post.nao.encontrado", postId)));

        ComentarioEntity comentario = new ComentarioEntity(dto.comentario(), post);

        post.adicionarComentario(comentario);
        ComentarioEntity saved = comentarioRepository.save(comentario);

        return comentarioMapper.toDto(saved);
    }

    @Override
    @Transactional
    public ComentarioResponseDto updateComentario(UUID comentarioId, ComentarioRequestDto dto) {
        ComentarioEntity comentario = comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        messageService.getMessage("comentario.nao.encontrado", comentarioId)));

        comentario.setComentario(dto.comentario());
        ComentarioEntity updated = comentarioRepository.save(comentario);
        return comentarioMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void deleteComentario(UUID comentarioId) {
        ComentarioEntity comentario = comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        messageService.getMessage("comentario.nao.encontrado", comentarioId)));
        comentarioRepository.deleteById(comentarioId);
    }


}
