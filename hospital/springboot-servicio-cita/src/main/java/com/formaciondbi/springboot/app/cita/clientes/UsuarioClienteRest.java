package com.formaciondbi.springboot.app.cita.clientes;

import com.formaciondbi.springboot.app.cita.models.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "servicio-usuarios", url = "http://localhost:8002")
public interface UsuarioClienteRest {

    @GetMapping("/listar")
    List<Usuario> listar();

    @GetMapping("/ver/{id}")
    Usuario detalle(@PathVariable Long id);

    @PostMapping("/crear")
    Usuario crear(@RequestBody Usuario usuario);

    @PutMapping("/editar/{id}")
    Usuario editar(@RequestBody Usuario usuario, @PathVariable Long id);

    @DeleteMapping("/eliminar/{id}")
    void eliminar(@PathVariable Long id);
}
