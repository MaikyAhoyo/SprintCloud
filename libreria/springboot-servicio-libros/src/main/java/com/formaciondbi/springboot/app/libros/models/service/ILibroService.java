package com.formaciondbi.springboot.app.libros.models.service;

import java.util.List;

import com.formaciondbi.springboot.app.libros.models.entity.Libro;

public interface ILibroService {
    List<Libro> findAll();
    Libro findById(Long id);
    Libro findByIsbn(long isbn);
    Libro save(Libro libro);
    void delete(Long id);
}
