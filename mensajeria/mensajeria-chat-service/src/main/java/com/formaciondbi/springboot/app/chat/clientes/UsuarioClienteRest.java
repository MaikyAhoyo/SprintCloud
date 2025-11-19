package com.formaciondbi.springboot.app.chat.clientes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.formaciondbi.springboot.app.chat.models.Usuario;

@FeignClient(name = "clientes-service")
public interface UsuarioClienteRest {
	
    @GetMapping("/listar/{id}")
    Usuario detalle(@PathVariable Long id);
    
}