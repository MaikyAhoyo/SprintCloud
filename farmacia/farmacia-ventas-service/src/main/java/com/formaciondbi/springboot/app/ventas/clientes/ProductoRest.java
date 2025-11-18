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

import com.formaciondbi.springboot.app.ventas.models.Producto;

@FeignClient(name = "producto-service")
public interface ProductoRest {

    @GetMapping("/listar")
    List<Producto> listar();

    @GetMapping("/listar/{id}")
    Producto detalle(@PathVariable Long id);
    
    @PostMapping("/crear")
    ResponseEntity<?> crear(@RequestBody Producto producto);
    
    @PutMapping("/actualizar/{id}")
    ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Producto producto);
    
    @DeleteMapping("/eliminar/{id}")
    void eliminar(@PathVariable Long id);
}