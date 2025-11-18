package com.formaciondbi.springboot.app.ventas.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.formaciondbi.springboot.app.ventas.models.Cliente;

@FeignClient(name = "clientes-service")
public interface ClienteRest {

    @GetMapping("/listar")
    List<Cliente> listar();

    @GetMapping("/listar/{id}")
    Cliente detalle(@PathVariable Long id);
    
    @GetMapping("/email/{email}")
    Cliente buscarPorEmail(@PathVariable String email);
    
    @PostMapping("/crear")
    ResponseEntity<?> crear(@RequestBody Cliente cliente);
    
    @PutMapping("/actualizar/{id}")
    ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Cliente cliente);
    
    @DeleteMapping("/eliminar/{id}")
    void eliminar(@PathVariable Long id);
}