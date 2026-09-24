package com.api.blog_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "comentario")
@Getter
@Setter
public class ComentarioEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private LocalDate data;

    @Lob
    @Column(nullable = false)
    private String comentario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post", nullable = false)
    private PostEntity post;

    public ComentarioEntity(LocalDate data, String comentario, PostEntity post) {
        this.data = data;
        this.comentario = comentario;
        this.post = post;
    }
}
