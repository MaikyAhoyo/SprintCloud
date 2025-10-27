package com.formaciondbi.springboot.app.vehiculos.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formaciondbi.springboot.app.vehiculos.models.dao.VehiculoDao;
import com.formaciondbi.springboot.app.vehiculos.models.entity.Vehiculo;

@Service
public class VehiculoServiceImpl implements IVehiculoService {

    @Autowired
    private VehiculoDao vehiculoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Vehiculo> findAll() {
        return (List<Vehiculo>) vehiculoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Vehiculo findById(Long id) {
        return vehiculoDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Vehiculo save(Vehiculo vehiculo) {
        return vehiculoDao.save(vehiculo);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        vehiculoDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehiculo> findByClienteId(Long clienteId) {
        return vehiculoDao.findByClienteId(clienteId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehiculo> findByMarca(String marca) {
        return vehiculoDao.findByMarcaIgnoreCase(marca);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehiculo> findByNumeroPlaca(String numeroPlaca) {
        return vehiculoDao.findByNumeroPlacaIgnoreCase(numeroPlaca);
    }
}
