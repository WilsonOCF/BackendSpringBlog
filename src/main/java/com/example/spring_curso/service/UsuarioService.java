package com.example.spring_curso.service;

import com.example.spring_curso.dto.input.UsuarioCreateDto;
import com.example.spring_curso.dto.output.ReniecResponseApi;
import com.example.spring_curso.dto.output.UsuarioResponse;
import com.example.spring_curso.entity.RoleEntity;
import com.example.spring_curso.entity.UsuarioEntity;
import com.example.spring_curso.feign.ReniecClient;
import com.example.spring_curso.repository.RoleRepository;
import com.example.spring_curso.repository.UsuarioRepository;
import com.example.spring_curso.service.utils.Mapper;
import com.example.spring_curso.service.utils.PasswordGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.rmi.MarshalledObject;
import java.sql.SQLOutput;
import java.util.*;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final ReniecClient reniecClient;
    @Value("${decolecta.api.token}")
    private String token;

    public UsuarioService(UsuarioRepository usuarioRepository, RoleRepository roleRepository, ReniecClient reniecClient) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.reniecClient = reniecClient;
    }

    public UsuarioResponse createUsuario(String dni){
        ReniecResponseApi reniecResponse = null;
        try {
            reniecResponse
                    = reniecClient.getInfoPersonal(dni, "Bearer " + token);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

        String password = PasswordGenerator.generatePassword(
                10,true,true,true,true
        );
        UsuarioEntity usuarioEntity = Mapper.fromUsuarioCreateDto(reniecResponse);
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
        RoleEntity roleEntity = roleOptional.get();
        Set<RoleEntity> roles = usuarioEntity.getRoles();
        roles.add(roleEntity);

        usuarioRepository.save(usuarioEntity);
        return Mapper.fromUsuarioEntity(usuarioEntity);
    }
}
