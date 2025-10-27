package com.formaciondbi.springboot.app.reparaciones.models.service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.formaciondbi.springboot.app.reparaciones.clientes.VehiculoClienteRest;
import com.formaciondbi.springboot.app.reparaciones.models.Reparacion;
import com.formaciondbi.springboot.app.reparaciones.models.Vehiculo;

@Service("serviceFeign")
@Primary
public class ReparacionesServiceFeign implements ReparacionesService {

    @Autowired
    private VehiculoClienteRest clienteFeign;

    private Map<Long, Reparacion> reparacionesDB = new HashMap<>();
    private AtomicLong idCounter = new AtomicLong(1);

    @Override
    public List<Reparacion> findAll() {
        return reparacionesDB.values().stream().map(r -> {
            Vehiculo v = clienteFeign.detalle(r.getVehiculoId());
            r.setVehiculo(v);
            return r;
        }).collect(Collectors.toList());
    }

    @Override
    public Reparacion findById(Long id) {
        Reparacion r = reparacionesDB.get(id);
        if (r != null) {
            Vehiculo v = clienteFeign.detalle(r.getVehiculoId());
            r.setVehiculo(v);
            return r;
        }
        return null;
    }

    @Override
    public Reparacion save(Reparacion reparacion) {
        Long newId = idCounter.getAndIncrement();
        reparacion.setId(newId);

        Vehiculo vehiculo = clienteFeign.detalle(reparacion.getVehiculoId());
        reparacion.setVehiculo(vehiculo);

        reparacion.setCreateAt(new Date());

        reparacionesDB.put(newId, reparacion);
        return reparacion;
    }

    @Override
    public Reparacion update(Long id, Reparacion reparacion) {
        Reparacion existing = reparacionesDB.get(id);
        if (existing != null) {
            existing.setDescripcion(reparacion.getDescripcion());
            existing.setEstado(reparacion.getEstado());
            existing.setCosto(reparacion.getCosto());
            existing.setVehiculoId(reparacion.getVehiculoId());

            Vehiculo v = clienteFeign.detalle(reparacion.getVehiculoId());
            existing.setVehiculo(v);

            reparacionesDB.put(id, existing);
            return existing;
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        reparacionesDB.remove(id);
    }

    @Override
    public List<Reparacion> findByVehiculoId(Long vehiculoId) {
        return reparacionesDB.values().stream()
                .filter(r -> r.getVehiculoId().equals(vehiculoId))
                .map(r -> {
                    Vehiculo v = clienteFeign.detalle(vehiculoId);
                    r.setVehiculo(v);
                    return r;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Reparacion> findByEstado(String estado) {
        return reparacionesDB.values().stream()
                .filter(r -> r.getEstado().equalsIgnoreCase(estado))
                .map(r -> {
                    Vehiculo v = clienteFeign.detalle(r.getVehiculoId());
                    r.setVehiculo(v);
                    return r;
                })
                .collect(Collectors.toList());
    }
}
