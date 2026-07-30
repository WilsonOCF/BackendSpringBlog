package com.example.spring_curso.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
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
    private Date fechaActualizacion;
}