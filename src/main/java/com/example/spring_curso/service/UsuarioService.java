package com.example.spring_curso.service;

import com.example.spring_curso.dto.input.UsuarioCreateDto;
import com.example.spring_curso.dto.output.UsuarioResponse;
import com.example.spring_curso.entity.RoleEntity;
import com.example.spring_curso.entity.UsuarioEntity;
import com.example.spring_curso.repository.RoleRepository;
import com.example.spring_curso.repository.UsuarioRepository;
import com.example.spring_curso.service.utils.Mapper;
import com.example.spring_curso.service.utils.PasswordGenerator;
import org.springframework.stereotype.Service;

import java.rmi.MarshalledObject;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, RoleRepository roleRepository) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
    }

    public UsuarioResponse createUsuario(UsuarioCreateDto usuarioCreateDto){
        String dni = usuarioCreateDto.getDni();
        String password = PasswordGenerator.generatePassword(
                10,true,true,true,true
        );
        UsuarioEntity usuarioEntity = Mapper.fromUsuarioCreateDto(usuarioCreateDto);
        usuarioEntity.setPassword(password);

        usuarioRepository.save(usuarioEntity);

        return Mapper.fromUsuarioEntity(usuarioEntity);
    }

    public UsuarioResponse finUsuarioById(UUID id){
        Optional<UsuarioEntity> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isEmpty()){
            return null;
        }
        UsuarioEntity usuarioEntity = optionalUsuario.get();
        return Mapper.fromUsuarioEntity(usuarioEntity);
    }

    public UsuarioResponse agregarRoleUsuario(String role, UUID idUsuario){
        Optional<RoleEntity> roleOptional = roleRepository.findByNombre(role);
        Optional<UsuarioEntity> usuarioOptional = usuarioRepository.findById(idUsuario);
        if(roleOptional.isEmpty() || usuarioOptional.isEmpty()){
            return null;
        }
        UsuarioEntity usuarioEntity = usuarioOptional.get();
        usuarioEntity.getRoles().add(roleOptional.get());

        usuarioRepository.save(usuarioEntity);
        return Mapper.fromUsuarioEntity(usuarioEntity);
    }
}
