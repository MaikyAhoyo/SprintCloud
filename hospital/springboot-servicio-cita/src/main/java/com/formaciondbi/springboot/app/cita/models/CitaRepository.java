package com.formaciondbi.springboot.app.cita.models;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaRepository extends JpaRepository<Cita, Long> {
	List<Cita> findByFechaCitaBetween(Date start, Date end);
	List<Cita> findByUsuarioId(Long usuarioId);
}
