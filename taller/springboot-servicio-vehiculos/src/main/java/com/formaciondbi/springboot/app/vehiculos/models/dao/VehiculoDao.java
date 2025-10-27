package com.formaciondbi.springboot.app.vehiculos.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.formaciondbi.springboot.app.vehiculos.models.entity.Vehiculo;

public interface VehiculoDao extends CrudRepository<Vehiculo, Long> {
	List<Vehiculo> findByClienteId(Long clienteId);
    List<Vehiculo> findByMarcaIgnoreCase(String marca);
    List<Vehiculo> findByNumeroPlacaIgnoreCase(String numeroPlaca);
}
