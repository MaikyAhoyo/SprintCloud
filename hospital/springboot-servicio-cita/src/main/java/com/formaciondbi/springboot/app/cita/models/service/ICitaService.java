package com.formaciondbi.springboot.app.cita.models.service;

import java.util.List;

import com.formaciondbi.springboot.app.cita.models.Cita;

public interface ICitaService {

	
	public List<Cita> findAll();
	public Cita findById(Long id);
	public List<Cita> findToday();
	List<Cita> findByUser(Long usuarioId);
	public Cita create(Cita cita);
	public Cita edit(Cita cita, Long id);
	public void delete(Long id);
}
