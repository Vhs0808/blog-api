package com.api.blog_api.repository;

import com.api.blog_api.entity.ComentarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ComentarioRepository extends JpaRepository<ComentarioEntity, UUID> { }
