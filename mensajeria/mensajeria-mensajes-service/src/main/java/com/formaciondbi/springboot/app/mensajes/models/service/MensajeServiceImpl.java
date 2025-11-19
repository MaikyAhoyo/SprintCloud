package com.formaciondbi.springboot.app.mensajes.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formaciondbi.springboot.app.mensajes.models.dao.MensajeDao;
import com.formaciondbi.springboot.app.mensajes.models.entity.Mensaje;

@Service
public class MensajeServiceImpl implements IMensajeService {

    @Autowired
    private MensajeDao mensajeDao;

    @Override
    @Transactional(readOnly = true)
    public List<Mensaje> findAll() {
        return mensajeDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Mensaje findById(Long id) {
        return mensajeDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Mensaje save(Mensaje mensaje) {
        return mensajeDao.save(mensaje);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        mensajeDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Mensaje> findByUsuarioId(Long usuarioId) {
        return mensajeDao.findByUsuarioId(usuarioId);
    }
}