package com.formaciondbi.springboot.app.usuarios.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.formaciondbi.springboot.app.usuarios.models.entity.Usuario;
import com.formaciondbi.springboot.app.usuarios.models.service.IUsuarioService;

@RestController
public class UsuarioController {
	
	@Autowired
	private Environment env;
	
	@Value("${server.port}")
	private Integer port;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping("/listar")
	public List<Usuario> listar(){
		return usuarioService.findAll().stream().map(usuario -> {
			usuario.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			return usuario;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/ver/{id}")
	public Usuario detalle(@PathVariable Long id) {
		Usuario usuario = usuarioService.findById(id);
		usuario.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		
		/*try {
			Thread.sleep(2000L);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}*/
		
		return usuario;
	}

	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario crear(@RequestBody Usuario usuario) {
		return usuarioService.create(usuario);
	}
	
	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario editar(@RequestBody Usuario usuario, @PathVariable Long id) {
		usuario.setId(id);
		return usuarioService.edit(usuario, id);
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
	    usuarioService.delete(id);
	}
}
