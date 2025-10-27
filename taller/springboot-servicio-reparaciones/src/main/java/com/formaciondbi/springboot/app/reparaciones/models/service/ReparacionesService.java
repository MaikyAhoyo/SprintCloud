package com.formaciondbi.springboot.app.reparaciones.models.service;

import java.util.List;

import com.formaciondbi.springboot.app.reparaciones.models.Reparacion;

public interface ReparacionesService {
    List<Reparacion> findAll();
    Reparacion findById(Long id);
    Reparacion save(Reparacion reparacion);
    Reparacion update(Long id, Reparacion reparacion);
    void deleteById(Long id);
    List<Reparacion> findByVehiculoId(Long vehiculoId);
    List<Reparacion> findByEstado(String estado);

}