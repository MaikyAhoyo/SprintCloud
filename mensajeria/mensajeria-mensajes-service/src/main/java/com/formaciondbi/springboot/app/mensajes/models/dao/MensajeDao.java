package com.formaciondbi.springboot.app.mensajes.models.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.formaciondbi.springboot.app.mensajes.models.entity.Mensaje;

public interface MensajeDao extends JpaRepository<Mensaje, Long> {
    List<Mensaje> findByUsuarioId(Long usuarioId);
}