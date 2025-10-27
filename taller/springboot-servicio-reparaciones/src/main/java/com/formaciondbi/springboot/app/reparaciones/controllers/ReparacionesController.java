package com.formaciondbi.springboot.app.reparaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.formaciondbi.springboot.app.reparaciones.models.Reparacion;
import com.formaciondbi.springboot.app.reparaciones.models.service.ReparacionesService;

@RestController
public class ReparacionesController {

    @Autowired
    private ReparacionesService reparacionesService;

    @GetMapping("/listar")
    public List<Reparacion> listar() {
        return reparacionesService.findAll();
    }

    @GetMapping("/listar/{id}")
    public Reparacion detalle(@PathVariable Long id) {
        return reparacionesService.findById(id);
    }

    @PostMapping("/crear")
    public Reparacion crear(@RequestBody Reparacion reparacion) {
        return reparacionesService.save(reparacion);
    }

    @PutMapping("/editar/{id}")
    public Reparacion editar(@PathVariable Long id, @RequestBody Reparacion reparacion) {
        return reparacionesService.update(id, reparacion);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id) {
        reparacionesService.deleteById(id);
    }

    @GetMapping("/vehiculo/{vehiculoId}")
    public List<Reparacion> reparacionesPorVehiculo(@PathVariable Long vehiculoId) {
        return reparacionesService.findByVehiculoId(vehiculoId);
    }

    @GetMapping("/estado/{estado}")
    public List<Reparacion> reparacionesPorEstado(@PathVariable String estado) {
        return reparacionesService.findByEstado(estado);
    }
}
