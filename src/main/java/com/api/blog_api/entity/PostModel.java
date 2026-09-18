package com.api.blog_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
public class PostModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 70)
    private String autor;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Lob
    @Column(columnDefinition = "text", nullable = false)
    private String texto;

    public PostModel(String texto, String titulo, LocalDate data, String autor) {
        this.texto = texto;
        this.titulo = titulo;
        this.data = data;
        this.autor = autor;
    }
}