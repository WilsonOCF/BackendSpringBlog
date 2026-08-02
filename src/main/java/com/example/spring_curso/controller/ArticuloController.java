package com.example.spring_curso.controller;

import com.example.spring_curso.dto.input.ArticuloCreateDto;
import com.example.spring_curso.dto.output.ArticuloCreateResponse;
import com.example.spring_curso.service.ArticuloService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articulos")
public class ArticuloController {

    private final ArticuloService articuloService;

    public ArticuloController(ArticuloService articuloService) {
        this.articuloService = articuloService;
    }

    @PostMapping("/save")
    public ResponseEntity<ArticuloCreateResponse> crearArticulo(
            @Valid @RequestBody ArticuloCreateDto articuloCreateDto) {

        ArticuloCreateResponse articuloResponse = articuloService
                .createArticulo(articuloCreateDto);

        if (articuloResponse == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(articuloResponse);
    }
}
