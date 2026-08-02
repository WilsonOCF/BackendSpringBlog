package com.example.spring_curso.service;

import com.example.spring_curso.dto.input.ArticuloCreateDto;
import com.example.spring_curso.dto.output.ArticuloCreateResponse;
import com.example.spring_curso.entity.ArticuloEntity;
import com.example.spring_curso.entity.UsuarioEntity;
import com.example.spring_curso.repository.ArticuloRepository;
import com.example.spring_curso.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ArticuloService {
    private final ArticuloRepository articuloRepository;
    private final UsuarioRepository usuarioRepository;

    public ArticuloService(ArticuloRepository articuloRepository,
                           UsuarioRepository usuarioRepository) {
        this.articuloRepository = articuloRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public ArticuloCreateResponse createArticulo(ArticuloCreateDto articuloCreateDto) {
        Optional<UsuarioEntity> optionalUsuario = usuarioRepository
                .findById(articuloCreateDto.getIdUsuario());
        if (optionalUsuario.isEmpty()) {
            return null;
        }

        String fechaActual = new Date(System.currentTimeMillis()).toString();

        ArticuloEntity articuloEntity = new ArticuloEntity();
        articuloEntity.setTitulo(articuloCreateDto.getTitulo());
        articuloEntity.setContenido(articuloCreateDto.getContenido());
        articuloEntity.setFechaCreacion(fechaActual);
        articuloEntity.setFechaActualizacion(fechaActual);
        articuloEntity.setUsuarioEntity(optionalUsuario.get());

        articuloRepository.save(articuloEntity);

        return new ArticuloCreateResponse(
                articuloEntity.getIdArticulo(),
                articuloEntity.getTitulo(),
                articuloEntity.getContenido(),
                articuloEntity.getFechaCreacion(),
                articuloEntity.getFechaActualizacion(),
                optionalUsuario.get().getNombre()
        );
    }
}