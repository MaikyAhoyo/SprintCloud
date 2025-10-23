package com.formaciondbi.springboot.app.cita.controllers;

import com.formaciondbi.springboot.app.cita.models.Cita;
import com.formaciondbi.springboot.app.cita.models.service.ICitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CitaController {

    @Autowired
    private ICitaService citaService;

    @GetMapping("/listar")
    public List<Cita> listar() {
        return citaService.findAll();
    }

    @GetMapping("/listar/{id}")
    public Cita detalle(@PathVariable Long id) {
        return citaService.findById(id);
    }
    
    @GetMapping("/listar/hoy")
    public List<Cita> listarCitasHoy() {
        return citaService.findToday();
    }
    
    @GetMapping("/listar/usuario/{usuarioId}")
    public List<Cita> listarPorUsuario(@PathVariable Long usuarioId) {
        return citaService.findByUser(usuarioId);
    }

    @PostMapping("/crear")
    public Cita crear(@RequestBody Cita cita) {
        return citaService.create(cita);
    }

    @PutMapping("/editar/{id}")
    public Cita editar(@RequestBody Cita cita, @PathVariable Long id) {
        return citaService.edit(cita, id);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id) {
        citaService.delete(id);
    }
}
