package com.formaciondbi.springboot.app.mensajes.models.service;

import java.util.List;
import com.formaciondbi.springboot.app.mensajes.models.entity.Mensaje;

public interface IMensajeService {
    List<Mensaje> findAll();
    Mensaje findById(Long id);
    Mensaje save(Mensaje mensaje);
    void delete(Long id);
    List<Mensaje> findByUsuarioId(Long usuarioId);
}