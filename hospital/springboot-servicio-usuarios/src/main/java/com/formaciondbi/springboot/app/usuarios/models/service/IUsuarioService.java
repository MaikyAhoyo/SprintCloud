package com.formaciondbi.springboot.app.usuarios.models.service;

import java.util.List;

import com.formaciondbi.springboot.app.usuarios.models.entity.Usuario;

public interface IUsuarioService {

	public List<Usuario> findAll();
	public Usuario findById(Long id);
	public Usuario create(Usuario usuario);
	public Usuario edit(Usuario usuario, long id);
	public void delete(Long id);
}
