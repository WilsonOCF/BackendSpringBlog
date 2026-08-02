package com.example.spring_curso.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "articulo", schema = "articles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticuloEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_articulo")
    private UUID idArticulo;
    private String titulo;
    private String contenido;
    @Column(name = "fecha_creacion")
    private String fechaCreacion;
    @Column(name = "fecha_actualizacion")
    private String fechaActualizacion;

    @ManyToOne
    @JoinColumn(name = "id_usuario_fk")
    private UsuarioEntity usuarioEntity;
}
