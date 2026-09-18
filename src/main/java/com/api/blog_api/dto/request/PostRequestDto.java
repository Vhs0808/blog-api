package com.api.blog_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostRequestDto(

    @NotBlank(message = "O autor é obrigatório")
    @Size(max = 70, message = "O autor deve ter no máximo 70 caracteres")
    String autor,

    @NotBlank(message = "O título não é obrigatório")
    @Size(max = 100, message = "O título deve ter no máximo 100 caracteres")
    String titulo,

    @NotBlank(message = "O texto deve ser obrigatório")
    String texto

){}
