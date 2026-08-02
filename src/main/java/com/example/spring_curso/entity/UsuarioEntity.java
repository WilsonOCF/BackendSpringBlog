package com.example.spring_curso.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario", schema = "users")
public class UsuarioEntity {
    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idUsuario;
    private String username;
    private String password;
    private String nombre;
    private String apellido;
    private boolean estado = true;
    private String dni;
    @Column(name = "fecha_creacion")
    private String fechaCreacion = new Date(System.currentTimeMillis()).toString();
    @Column(name = "fecha_actualizacion")
    private LocalDate fechaActualizacion;
    @Column(name = "numero_articulos", columnDefinition = "INTEGER DEFAULT 0")
    private Integer numeroArticulos =0;
    @Column(name = "numero_comentarios")
    private Integer numeroComentarios = 0;

    @ManyToMany
    @JoinTable(
        name = "usuario_role",
        schema = "users",
        joinColumns = @JoinColumn(name = "id_usuario_fk"),
        inverseJoinColumns = @JoinColumn(name = "id_role_fk")
    )
    private Set<RoleEntity> roles = new HashSet<>();
}