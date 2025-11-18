package com.formaciondbi.springboot.app.ventas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.formaciondbi.springboot.app.ventas.models.Venta;
import com.formaciondbi.springboot.app.ventas.models.service.VentasService;

@RestController
public class VentasController {

    @Autowired
    private VentasService ventasService;

    @GetMapping("/listar")
    public List<Venta> listar() {
        return ventasService.findAll();
    }

    @GetMapping("/listar/{id}")
    public Venta detalle(@PathVariable Long id) {
        return ventasService.findById(id);
    }

    @PostMapping("/crear")
    public Venta crear(@RequestBody Venta venta) {
        return ventasService.save(venta);
    }

    @PutMapping("/editar/{id}")
    public Venta editar(@PathVariable Long id, @RequestBody Venta venta) {
        return ventasService.update(id, venta);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id) {
        ventasService.deleteById(id);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<Venta> reparacionesPorCliente(@PathVariable Long clienteId) {
        return ventasService.findByClienteId(clienteId);
    }
}
