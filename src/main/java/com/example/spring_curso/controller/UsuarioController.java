package com.example.spring_curso.controller;

import com.example.spring_curso.dto.input.UsuarioCreateDto;
import com.example.spring_curso.dto.output.UsuarioCreateResponse;
import com.example.spring_curso.entity.UsuarioEntity;
import com.example.spring_curso.repository.UsuarioRepository;
import com.example.spring_curso.utils.PasswordGenerator;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/save")
    public UsuarioCreateResponse crearUsuario(@RequestBody UsuarioCreateDto usuarioCreateDto){
        String dni = usuarioCreateDto.getDni();
        if(dni.length() !=8){
            return null;
        }
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

    @GetMapping("/{id}")
    public UsuarioCreateResponse findById(@PathVariable UUID id){
        Optional<UsuarioEntity> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isEmpty()){
            throw new RuntimeException("Usuario no encontrado");
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
