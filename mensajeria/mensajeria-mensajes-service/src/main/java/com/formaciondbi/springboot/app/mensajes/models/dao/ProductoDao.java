package com.formaciondbi.springboot.app.mensajes.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.formaciondbi.springboot.app.mensajes.models.entity.Producto;

public interface ProductoDao extends CrudRepository<Producto, Long> {

}
