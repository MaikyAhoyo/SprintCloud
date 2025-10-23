package com.formaciondbi.springboot.app.usuarios.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formaciondbi.springboot.app.usuarios.models.dao.UsuarioDao;
import com.formaciondbi.springboot.app.usuarios.models.entity.Usuario;


@Service
public class UsuarioServiceImpl implements IUsuarioService{
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public Usuario create(Usuario usuario) {
		return usuarioDao.save(usuario);
	}

	@Override
	@Transactional
	public Usuario edit(Usuario usuario, long id) {
		return usuarioDao.save(usuario);
	}
	
    @Override
    @Transactional
    public void delete(Long id) {
        usuarioDao.deleteById(id);
    }
}