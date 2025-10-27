package com.formaciondbi.springboot.app.reparaciones.models.service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.formaciondbi.springboot.app.reparaciones.models.Reparacion;
import com.formaciondbi.springboot.app.reparaciones.models.Vehiculo;

@Service("serviceRestTemplate")
public class ReparacionesServiceImpl implements ReparacionesService {

    @Autowired
    private RestTemplate clienteRest;

    private Map<Long, Reparacion> reparacionesDB = new HashMap<>();
    private AtomicLong idCounter = new AtomicLong(1);

    @Override
    public List<Reparacion> findAll() {
        return new ArrayList<>(reparacionesDB.values());
    }

    @Override
    public Reparacion findById(Long id) {
        return reparacionesDB.get(id);
    }

    @Override
    public Reparacion save(Reparacion reparacion) {
        Long newId = idCounter.getAndIncrement();
        reparacion.setId(newId);

        Vehiculo vehiculo = clienteRest.getForObject(
                "http://servicio-vehiculos/ver/{id}",
                Vehiculo.class,
                reparacion.getVehiculoId()
        );
        reparacion.setVehiculo(vehiculo);
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

            Vehiculo vehiculo = clienteRest.getForObject(
                    "http://servicio-vehiculos/ver/{id}",
                    Vehiculo.class,
                    reparacion.getVehiculoId()
            );
            existing.setVehiculo(vehiculo);
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
                .collect(Collectors.toList());
    }

    @Override
    public List<Reparacion> findByEstado(String estado) {
        return reparacionesDB.values().stream()
                .filter(r -> r.getEstado().equalsIgnoreCase(estado))
                .collect(Collectors.toList());
    }
}
