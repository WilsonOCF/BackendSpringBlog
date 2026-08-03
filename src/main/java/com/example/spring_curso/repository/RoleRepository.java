package com.example.spring_curso.repository;

import com.example.spring_curso.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
    @Query(nativeQuery = true, value = "select * from users.role where nombre = :nombre")
    Optional<RoleEntity> findByNombre(String nombre);
}
