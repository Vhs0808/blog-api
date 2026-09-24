package com.api.blog_api.service;

import com.api.blog_api.entity.ComentarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ComentarioService extends JpaRepository<ComentarioEntity, UUID> {
}
