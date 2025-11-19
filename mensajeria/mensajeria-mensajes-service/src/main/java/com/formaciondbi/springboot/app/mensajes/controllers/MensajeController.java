package com.formaciondbi.springboot.app.mensajes.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.formaciondbi.springboot.app.mensajes.models.entity.Mensaje;
import com.formaciondbi.springboot.app.mensajes.models.service.IMensajeService;

@RestController
public class MensajeController {

    @Autowired
    private Environment env;

    @Value("${server.port}")
    private Integer port;

    @Autowired
    private IMensajeService mensajeService;

    @GetMapping("/listar")
    public List<Mensaje> listar() {
        return mensajeService.findAll().stream().map(mensaje -> {
            mensaje.setPort(Integer.parseInt(env.getProperty("local.server.port")));
            return mensaje;
        }).collect(Collectors.toList());
    }

    @GetMapping("/ver/{id}")
    public ResponseEntity<?> ver(@PathVariable Long id) {
        Mensaje mensaje = mensajeService.findById(id);
        if (mensaje == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mensaje no encontrado");
        }
        mensaje.setPort(Integer.parseInt(env.getProperty("local.server.port")));
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> listarPorUsuario(@PathVariable Long usuarioId) {
        List<Mensaje> mensajes = mensajeService.findByUsuarioId(usuarioId).stream().map(m -> {
            m.setPort(Integer.parseInt(env.getProperty("local.server.port")));
            return m;
        }).collect(Collectors.toList());
        
        return ResponseEntity.ok(mensajes);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody Mensaje mensaje) {
        if (mensaje.getUsuarioId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Se requiere el ID del usuario");
        }
        Mensaje mensajeDb = mensajeService.save(mensaje);
        mensajeDb.setPort(Integer.parseInt(env.getProperty("local.server.port")));
        return ResponseEntity.status(HttpStatus.CREATED).body(mensajeDb);
    }
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        mensajeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}