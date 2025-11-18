package com.formaciondbi.springboot.app.mensajes.models.service;

import java.util.List;

import com.formaciondbi.springboot.app.mensajes.models.entity.Producto;

public interface IProductoService {

	public List<Producto> findAll();
	public Producto findById(Long id);
	Producto save(Producto producto);
    void delete(Long id);
}
