package com.example.spring_curso.controller;

import com.example.spring_curso.dto.input.UsuarioCreateDto;
import com.example.spring_curso.dto.output.UsuarioCreateResponse;
import com.example.spring_curso.service.UsuarioService;
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
    public ResponseEntity<UsuarioCreateResponse> crearUsuario(@RequestBody UsuarioCreateDto usuarioCreateDto){
        UsuarioCreateResponse userResponse = usuarioService.createUsuario(usuarioCreateDto);
        if (userResponse ==null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioCreateResponse> findById(@PathVariable UUID id){
        UsuarioCreateResponse userResponse = usuarioService.finUsuarioById(id);
        if (userResponse ==null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(userResponse);
    }
}
