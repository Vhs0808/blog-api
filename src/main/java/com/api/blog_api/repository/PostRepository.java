package com.api.blog_api.repository;

import com.api.blog_api.entity.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<PostModel, UUID> {}