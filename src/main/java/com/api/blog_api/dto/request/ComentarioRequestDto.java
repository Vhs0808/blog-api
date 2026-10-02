package com.api.blog_api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ComentarioRequestDto(
        @NotBlank(message = "{comentario.obrigatorio}")
        String comentario
) {
}
