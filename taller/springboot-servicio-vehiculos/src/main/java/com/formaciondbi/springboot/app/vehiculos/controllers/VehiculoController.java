package com.formaciondbi.springboot.app.vehiculos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.formaciondbi.springboot.app.vehiculos.models.entity.Vehiculo;
import com.formaciondbi.springboot.app.vehiculos.models.service.IVehiculoService;

@RestController
public class VehiculoController {

    @Autowired
    private Environment env;

    @Value("${server.port}")
    private Integer port;

    @Autowired
    private IVehiculoService vehiculoService;

    @GetMapping("/listar")
    public List<Vehiculo> listar() {
        return vehiculoService.findAll().stream().map(vehiculo -> {
            vehiculo.setPort(Integer.parseInt(env.getProperty("local.server.port")));
            return vehiculo;
        }).collect(Collectors.toList());
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Vehiculo> detalle(@PathVariable Long id) {
        Vehiculo vehiculo = vehiculoService.findById(id);
        if (vehiculo == null) {
            return ResponseEntity.notFound().build();
        }
        vehiculo.setPort(Integer.parseInt(env.getProperty("local.server.port")));

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(vehiculo);
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Vehiculo crear(@RequestBody Vehiculo vehiculo) {
        return vehiculoService.save(vehiculo);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Vehiculo> editar(@RequestBody Vehiculo vehiculo, @PathVariable Long id) {
        Vehiculo actual = vehiculoService.findById(id);
        if (actual == null) {
            return ResponseEntity.notFound().build();
        }

        actual.setMarca(vehiculo.getMarca());
        actual.setModelo(vehiculo.getModelo());
        actual.setAnio(vehiculo.getAnio());
        actual.setColor(vehiculo.getColor());
        actual.setNumeroPlaca(vehiculo.getNumeroPlaca());
        actual.setNumeroSerie(vehiculo.getNumeroSerie());
        actual.setClienteId(vehiculo.getClienteId());
        actual.setActivo(vehiculo.isActivo());

        return ResponseEntity.ok(vehiculoService.save(actual));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Vehiculo vehiculo = vehiculoService.findById(id);
        if (vehiculo == null) {
            return ResponseEntity.notFound().build();
        }
        vehiculoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Vehiculo>> listarPorCliente(@PathVariable Long clienteId) {
        List<Vehiculo> vehiculos = vehiculoService.findByClienteId(clienteId);
        if (vehiculos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<Vehiculo>> listarPorMarca(@PathVariable String marca) {
        List<Vehiculo> vehiculos = vehiculoService.findByMarca(marca);
        if (vehiculos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/placa/{placa}")
    public ResponseEntity<List<Vehiculo>> listarPorPlaca(@PathVariable String placa) {
        List<Vehiculo> vehiculos = vehiculoService.findByNumeroPlaca(placa);
        if (vehiculos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vehiculos);
    }
}
