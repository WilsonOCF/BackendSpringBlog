package com.example.spring_curso.controller;

import com.example.spring_curso.dto.input.ArticuloCreateDto;
import com.example.spring_curso.dto.input.ArticuloUpdateDto;
import com.example.spring_curso.dto.output.ArticuloCreateResponse;
import com.example.spring_curso.entity.ArticuloEntity;
import com.example.spring_curso.service.ArticuloService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @PatchMapping("/update/{id}")
    public ResponseEntity<ArticuloCreateResponse> updateArticulo(
            @RequestBody ArticuloUpdateDto articuloUpdateDto,
            @PathVariable(name = "id") UUID idArticulo){
        ArticuloCreateResponse acr = articuloService.updateArticulo(idArticulo, articuloUpdateDto);
        if(acr ==null){
            return ResponseEntity.badRequest().build();
        }else {
            return ResponseEntity.accepted().body(acr);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteArticulo(
            @PathVariable UUID idArticulo,
            @RequestParam boolean idDraft){
        articuloService.deleteArticulo(idArticulo,idDraft);
        return ResponseEntity.noContent().build();
    }
}
