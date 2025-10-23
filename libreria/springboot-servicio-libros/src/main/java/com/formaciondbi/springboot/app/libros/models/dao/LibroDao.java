package com.formaciondbi.springboot.app.libros.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.formaciondbi.springboot.app.libros.models.entity.Libro;

public interface LibroDao extends CrudRepository<Libro, Long> {
	Libro findByISBN(long isbn);
}
