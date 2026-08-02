package com.example.spring_curso.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ArticuloCreateDto {
    @NotBlank(message = "El titulo es obligatorio")
    private String titulo;

    @NotBlank(message = "El contenido es obligatorio")
    private String contenido;

    @NotNull(message = "el id del usuario es obligatorio")
    private UUID idUsuario;
}
