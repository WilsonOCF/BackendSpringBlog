package com.example.spring_curso.repository;

import com.example.spring_curso.entity.ArticuloEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArticuloRepository extends JpaRepository<ArticuloEntity, UUID> {
}
