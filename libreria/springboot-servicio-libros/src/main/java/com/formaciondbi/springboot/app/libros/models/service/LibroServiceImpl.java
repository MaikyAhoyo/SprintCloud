package com.formaciondbi.springboot.app.libros.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formaciondbi.springboot.app.libros.models.dao.LibroDao;
import com.formaciondbi.springboot.app.libros.models.entity.Libro;

@Service
public class LibroServiceImpl implements ILibroService {

    @Autowired
    private LibroDao libroDao;

    @Override
    @Transactional(readOnly = true)
    public List<Libro> findAll() {
        return (List<Libro>) libroDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Libro findById(Long id) {
        return libroDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Libro findByIsbn(long isbn) {
        return libroDao.findByISBN(isbn);
    }
    
    @Override
    public Libro save(Libro libro) {
        return libroDao.save(libro);
    }

    @Override
    public void delete(Long id) {
        libroDao.deleteById(id);
    }
}