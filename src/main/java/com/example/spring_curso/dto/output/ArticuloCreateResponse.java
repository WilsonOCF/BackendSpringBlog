package com.example.spring_curso.dto.output;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloCreateResponse {
    private UUID idArticulo;
    private String titulo;
    private String contenido;
    private String fechaCreacion;
    private String fechaActualizacion;
    private String nombreUsuario;
}
