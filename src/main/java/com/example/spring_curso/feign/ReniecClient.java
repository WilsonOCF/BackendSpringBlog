package com.example.spring_curso.feign;

import com.example.spring_curso.dto.output.ReniecResponseApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "reniec-service", url = "https://api.decolecta.com/v1/reniec/dni")
public interface ReniecClient{
    @GetMapping
    ReniecResponseApi getInfoPersonal(
            @RequestParam String numero,
            @RequestHeader("Authorization") String token);
}
