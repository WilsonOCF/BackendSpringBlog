package com.example.spring_curso.service;

import com.example.spring_curso.dto.input.UsuarioCreateDto;
import com.example.spring_curso.dto.output.UsuarioCreateResponse;
import com.example.spring_curso.entity.UsuarioEntity;
import com.example.spring_curso.repository.UsuarioRepository;
import com.example.spring_curso.service.utils.PasswordGenerator;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioCreateResponse createUsuario(UsuarioCreateDto usuarioCreateDto){
        String dni = usuarioCreateDto.getDni();
        String password = PasswordGenerator.generatePassword(
                10,true,true,true,true
        );
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setUsername(usuarioCreateDto.getUsername());
        usuarioEntity.setPassword(password);
        usuarioEntity.setNombre(usuarioCreateDto.getNombre());
        usuarioEntity.setApellido(usuarioCreateDto.getApellido());
        usuarioEntity.setDni(usuarioCreateDto.getDni());

        usuarioRepository.save(usuarioEntity);

        return new UsuarioCreateResponse(
                usuarioEntity.getIdUsuario(),
                usuarioEntity.getUsername(),
                usuarioEntity.getNombre(),
                usuarioEntity.getApellido(),
                usuarioEntity.getDni(),
                usuarioEntity.isEstado()
        );
    }

    public UsuarioCreateResponse finUsuarioById(UUID id){
        Optional<UsuarioEntity> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isEmpty()){
            return null;
        }
        UsuarioEntity usuarioEntity = optionalUsuario.get();
        return new UsuarioCreateResponse(
                usuarioEntity.getIdUsuario(),
                usuarioEntity.getUsername(),
                usuarioEntity.getNombre(),
                usuarioEntity.getApellido(),
                usuarioEntity.getDni(),
                usuarioEntity.isEstado()
        );
    }
}
