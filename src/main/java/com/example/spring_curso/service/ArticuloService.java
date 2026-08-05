package com.example.spring_curso.service;

import com.example.spring_curso.dto.input.ArticuloCreateDto;
import com.example.spring_curso.dto.input.ArticuloUpdateDto;
import com.example.spring_curso.dto.output.ArticuloCreateResponse;
import com.example.spring_curso.entity.ArticuloEntity;
import com.example.spring_curso.entity.UsuarioEntity;
import com.example.spring_curso.repository.ArticuloRepository;
import com.example.spring_curso.repository.UsuarioRepository;
import com.example.spring_curso.service.utils.Mapper;
import org.springframework.stereotype.Service;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

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

        ArticuloEntity articuloEntity = Mapper.fromArticuloCreateDto(articuloCreateDto);
        articuloEntity.setFechaCreacion(fechaActual);
        articuloEntity.setUsuarioEntity(optionalUsuario.get());

        articuloRepository.save(articuloEntity);

        return Mapper.fromArticuloEntity(articuloEntity);
    }

    public ArticuloCreateResponse updateArticulo(UUID id, ArticuloUpdateDto articuloUpdateDto){
        Optional<ArticuloEntity> optionalArticuloEntity = articuloRepository.findById(id);
        if(optionalArticuloEntity.isEmpty()){
            return null;
        }
        ArticuloEntity articuloEntity = optionalArticuloEntity.get();
        String nuevoTitulo = articuloUpdateDto.getTitulo();
        String nuevoContenido = articuloUpdateDto.getContenido();
        if(nuevoTitulo != null && nuevoContenido != null){
            articuloEntity.setTitulo(nuevoTitulo);
            articuloEntity.setContenido(nuevoContenido);
        } else if (nuevoTitulo != null) {
            articuloEntity.setTitulo(nuevoTitulo);
        } else if (nuevoContenido != null) {
            articuloEntity.setContenido(nuevoContenido);
        }else {
            return null;
        }

        articuloEntity.setFechaActualizacion(new Date(System.currentTimeMillis()).toString());
        articuloRepository.save(articuloEntity);

        return Mapper.fromArticuloEntity(articuloEntity);
    }
}