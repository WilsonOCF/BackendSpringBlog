package com.example.spring_curso.service.utils;

import com.example.spring_curso.dto.input.ArticuloCreateDto;
import com.example.spring_curso.dto.input.UsuarioCreateDto;
import com.example.spring_curso.dto.output.ArticuloCreateResponse;
import com.example.spring_curso.dto.output.ReniecResponseApi;
import com.example.spring_curso.dto.output.UsuarioResponse;
import com.example.spring_curso.entity.ArticuloEntity;
import com.example.spring_curso.entity.RoleEntity;
import com.example.spring_curso.entity.UsuarioEntity;
import org.hibernate.type.descriptor.jdbc.UuidAsBinaryJdbcType;

import java.util.ArrayList;

public class Mapper {
    //--------------------
    //  Primer Mapper
    //-------------------
    public static UsuarioResponse fromUsuarioEntity(UsuarioEntity usuarioEntity){
        ArrayList<String> roles = new ArrayList<>();

        for(RoleEntity role: usuarioEntity.getRoles()){
            roles.add(role.getNombre());
        }

        return new UsuarioResponse(
                usuarioEntity.getIdUsuario(),
                usuarioEntity.getUsername(),
                usuarioEntity.getNombre(),
                usuarioEntity.getApellido(),
                usuarioEntity.getDni(),
                usuarioEntity.isEstado(),
                roles
        );
    }

    //---------------------
    //  Segundo Mapper
    //--------------------
    public static UsuarioEntity fromUsuarioCreateDto(ReniecResponseApi reniecResponseApi){
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setUsername(reniecResponseApi.getDocumentNumber());
        usuarioEntity.setNombre(reniecResponseApi.getFirstName());
        usuarioEntity.setApellido(reniecResponseApi.getFirstLastName());
        usuarioEntity.setDni(reniecResponseApi.getDocumentNumber());
        return usuarioEntity;
    }

    public static ArticuloEntity fromArticuloCreateDto(ArticuloCreateDto articuloCreateDto){
        ArticuloEntity articuloEntity = new ArticuloEntity();
        articuloEntity.setTitulo(articuloCreateDto.getTitulo());
        articuloEntity.setContenido(articuloCreateDto.getContenido());
        return articuloEntity;
    }

    public static ArticuloCreateResponse fromArticuloEntity(ArticuloEntity articuloEntity){
        ArticuloCreateResponse articuloCreateResponse = new ArticuloCreateResponse();
        articuloCreateResponse.setIdArticulo(articuloEntity.getIdArticulo());
        articuloCreateResponse.setTitulo(articuloEntity.getTitulo());
        articuloCreateResponse.setContenido(articuloEntity.getContenido());
        articuloCreateResponse.setFechaCreacion(articuloEntity.getFechaCreacion());
        articuloCreateResponse.setFechaActualizacion(articuloEntity.getFechaActualizacion());
        articuloCreateResponse.setNombreUsuario(articuloEntity.getUsuarioEntity().getNombre());
        return articuloCreateResponse;
    }
}
