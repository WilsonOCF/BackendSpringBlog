package com.example.spring_curso.controller;

import com.example.spring_curso.dto.input.UsuarioCreateDto;
import com.example.spring_curso.dto.output.UsuarioResponse;
import com.example.spring_curso.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/save")
    public ResponseEntity<UsuarioResponse> crearUsuario(@RequestParam String dni){
        UsuarioResponse userResponse = usuarioService.createUsuario(dni);
        if (userResponse ==null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> findById(@PathVariable UUID id){
        UsuarioResponse userResponse = usuarioService.finUsuarioById(id);
        if (userResponse ==null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/usuario/role")
    public ResponseEntity<UsuarioResponse> addRoleToUser(
            @RequestParam String role, @RequestParam UUID usuarioId
    ){
        UsuarioResponse usuarioResponse = usuarioService.agregarRoleUsuario(role, usuarioId);
        if(usuarioResponse == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(usuarioResponse);

    }
}
