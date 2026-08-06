package com.example.spring_curso;

import com.example.spring_curso.entity.UsuarioEntity;
import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Scanner;

@SpringBootApplication
@EnableFeignClients
public class SpringCursoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCursoApplication.class, args);

	}
}
