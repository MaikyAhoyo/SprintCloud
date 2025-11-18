package com.formaciondbi.springboot.app.ventas.models.service;

import java.util.List;

import com.formaciondbi.springboot.app.ventas.models.Venta;

public interface VentasService {
    List<Venta> findAll();
    Venta findById(Long id);
    Venta save(Venta venta);
    Venta update(Long id, Venta venta);
    void deleteById(Long id);
    List<Venta> findByClienteId(Long clienteId);
}