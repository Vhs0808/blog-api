package com.api.blog_api.repository;

import com.api.blog_api.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<PostEntity, UUID> {
    Page<PostEntity> findByTituloContainingIgnoreCase
            (String titulo, Pageable pageable);
}