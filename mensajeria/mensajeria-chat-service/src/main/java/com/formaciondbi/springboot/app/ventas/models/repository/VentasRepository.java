package com.formaciondbi.springboot.app.ventas.models.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.formaciondbi.springboot.app.ventas.models.Venta;

public interface VentasRepository extends JpaRepository<Venta, Long> {
    List<Venta> findByClienteId(Long clienteId);
}