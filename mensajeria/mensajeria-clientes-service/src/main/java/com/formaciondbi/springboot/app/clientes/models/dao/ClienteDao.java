package com.formaciondbi.springboot.app.clientes.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formaciondbi.springboot.app.clientes.models.entity.Cliente;

public interface ClienteDao extends JpaRepository<Cliente, Long> {
    Cliente findByNombre(String nombre);
    Cliente findByEmail(String email);
}