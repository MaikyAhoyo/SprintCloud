package com.formaciondbi.springboot.app.mensajes.controllers;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.formaciondbi.springboot.app.mensajes.models.entity.Producto;
import com.formaciondbi.springboot.app.mensajes.models.service.IProductoService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ProductoController {
	
	@Autowired
	private Environment env;
	
	@Value("${server.port}")
	private Integer port;
	
	@Autowired
	private IProductoService productoService;
	
	@GetMapping("/listar")
	public List<Producto> listar(){
		return productoService.findAll().stream().map(producto -> {
			producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			return producto;
		}).collect(Collectors.toList());
	}
	
	@HystrixCommand(fallbackMethod = "MetodoAlternativo")
	@GetMapping("/listar/{id}")
	public Producto detalle(@PathVariable Long id) {
		Producto producto = productoService.findById(id);
		producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		//producto.setPort(port);
		
		try {
			Thread.sleep(2000L);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		return producto;
	}
	
	public Producto MetodoAlternativo(Long id) {
	    Producto producto = new Producto();
	    Date date = new Date();
	    
	    producto.setNombre("Sopita");
	    producto.setPrecio(900);
	    producto.setCreateAt(date);
	    producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
	    
	    return producto;
	}

	@PostMapping("/crear")
	public ResponseEntity<?> registrarNuevoProducto(@RequestBody Producto producto) {
	    producto.setCreateAt(new Date());
	    Producto nuevoProducto = productoService.save(producto);

	    return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
	    Producto productoExistente = productoService.findById(id);

	    if (productoExistente == null) {
	        return ResponseEntity
	                .status(HttpStatus.NOT_FOUND)
	                .body("No existe un producto con el ID: " + id);
	    }

	    productoExistente.setNombre(producto.getNombre());
	    productoExistente.setPrecio((double) producto.getPrecio());

	    Producto actualizado = productoService.save(productoExistente);

	    return ResponseEntity.ok(actualizado);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminarProducto(@PathVariable Long id) {
	    productoService.delete(id);
	}
}