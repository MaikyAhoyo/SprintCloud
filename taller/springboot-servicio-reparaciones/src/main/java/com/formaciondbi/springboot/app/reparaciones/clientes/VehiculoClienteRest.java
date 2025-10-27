package com.formaciondbi.springboot.app.reparaciones.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.formaciondbi.springboot.app.reparaciones.models.Vehiculo;

@FeignClient(name = "servicio-vehiculos")
public interface VehiculoClienteRest {

    @GetMapping("/listar")
    List<Vehiculo> listar();

    @GetMapping("/listar/{id}")
    Vehiculo detalle(@PathVariable Long id);
}
