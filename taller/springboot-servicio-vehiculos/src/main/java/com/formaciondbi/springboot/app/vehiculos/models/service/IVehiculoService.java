package com.formaciondbi.springboot.app.vehiculos.models.service;

import java.util.List;

import com.formaciondbi.springboot.app.vehiculos.models.entity.Vehiculo;

public interface IVehiculoService {
    List<Vehiculo> findAll();
    Vehiculo findById(Long id);
    Vehiculo save(Vehiculo vehiculo);
    void deleteById(Long id);
    List<Vehiculo> findByClienteId(Long clienteId);
    List<Vehiculo> findByMarca(String marca);
    List<Vehiculo> findByNumeroPlaca(String numeroPlaca);
}
