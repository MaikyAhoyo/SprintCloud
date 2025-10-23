package com.formaciondbi.springboot.app.usuarios.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.formaciondbi.springboot.app.usuarios.models.entity.Usuario;

public interface UsuarioDao extends CrudRepository<Usuario, Long> {
	Usuario findByEmail(String email);
}
