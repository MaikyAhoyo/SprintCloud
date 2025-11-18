package com.formaciondbi.springboot.app.clientes.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.formaciondbi.springboot.app.clientes.models.entity.Cliente;

public interface ClienteDao extends CrudRepository<Cliente, Long> {
	Cliente findByEmail(String email);
}
