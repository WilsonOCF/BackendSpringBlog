package com.example.spring_curso.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comentario")
public class ComentarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_comentario")
    private UUID idComentario;
    private String contenido;
    @Column(name = "fecha_creacion")
    private String fechaCreacion;
    @Column(name = "fecha_actualizacion")
    private String fechaActualizacion;

    @ManyToOne
    @JoinColumn(name = "id_articulo_fk")
    private ArticuloEntity articuloEntity;

    @ManyToOne
    @JoinColumn(name = "id_usuario_fk")
    private UsuarioEntity usuarioEntity;
}
