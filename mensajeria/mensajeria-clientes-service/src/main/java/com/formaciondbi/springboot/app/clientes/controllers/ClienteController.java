package com.formaciondbi.springboot.app.clientes.controllers;

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

import com.formaciondbi.springboot.app.clientes.models.entity.Cliente;
import com.formaciondbi.springboot.app.clientes.models.service.IClienteService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ClienteController {
	
	@Autowired
	private Environment env;
	
	@Value("${server.port}")
	private Integer port;
	
	
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/listar")
	public List<Cliente> listarTodosLosClientes(){
		return clienteService.findAll().stream().map(cliente -> {
			cliente.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			return cliente;
		}).collect(Collectors.toList());
	}
	
	@HystrixCommand(fallbackMethod = "MetodoAlternativo")
	@GetMapping("/listar/{id}")
	public Cliente consultarClientePorId(@PathVariable Long id) {
	    Cliente cliente = clienteService.findById(id);
	    if (cliente == null) {
	        throw new RuntimeException("Cliente no encontrado con ID: " + id);
	    }
	    cliente.setPort(Integer.parseInt(env.getProperty("local.server.port")));
	    return cliente;
	}

	public Cliente MetodoAlternativo(Long id) {
	    Cliente cliente = new Cliente();
	    Date date = new Date();
	    
	    cliente.setNombre("Gael");
	    cliente.setEdad(18);
	    cliente.setEmail("papupro@gmail.com");
	    cliente.setCreateAt(date);
	    cliente.setPort(Integer.parseInt(env.getProperty("local.server.port")));
	    
	    return cliente;
	}
	
	@GetMapping("/nombre/{nombre}")
	public Cliente nombre(String nombre) {
		Cliente cliente = clienteService.findByNombre(nombre);
		cliente.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		return cliente;
	}

	@GetMapping("/email/{email}")
	public Cliente email(@PathVariable String email) {
		Cliente cliente = clienteService.findByEmail(email);
		cliente.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		return cliente;
	}
	
	@PostMapping("/crear")
	public ResponseEntity<?> registrarNuevoCliente(@RequestBody Cliente cliente) {
	    Cliente clienteEmail = clienteService.findByEmail(cliente.getEmail());
	    if (clienteEmail != null) {
	        return ResponseEntity
	                .status(HttpStatus.CONFLICT)
	                .body("Ya existe un usuario con el Email: " + cliente.getEmail());
	    }
	    
	    Cliente clienteNombre = clienteService.findByNombre(cliente.getNombre());
	    if (clienteNombre != null) {
	        return ResponseEntity
	                .status(HttpStatus.CONFLICT)
	                .body("Ya existe un usuario con el Nombre: " + cliente.getNombre());
	    }

	    cliente.setCreateAt(new Date());
	    Cliente nuevoCliente = clienteService.save(cliente);

	    return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
	    Cliente clienteExistente = clienteService.findById(id);

	    if (clienteExistente == null) {
	        return ResponseEntity
	                .status(HttpStatus.NOT_FOUND)
	                .body("No existe un cliente con el ID: " + id);
	    }

	    Cliente clienteEmail = clienteService.findByEmail(cliente.getEmail());
	    if (clienteEmail != null && !clienteEmail.getId().equals(id)) {
	        return ResponseEntity
	                .status(HttpStatus.CONFLICT)
	                .body("Ya existe un usuario con el Email: " + cliente.getEmail());
	    }

	    Cliente clienteNombre = clienteService.findByNombre(cliente.getNombre());
	    if (clienteNombre != null && !clienteNombre.getId().equals(id)) {
	        return ResponseEntity
	                .status(HttpStatus.CONFLICT)
	                .body("Ya existe un usuario con el Nombre: " + cliente.getNombre());
	    }

	    clienteExistente.setNombre(cliente.getNombre());
	    clienteExistente.setEdad((int) cliente.getEdad());
	    clienteExistente.setEmail(cliente.getEmail());

	    Cliente actualizado = clienteService.save(clienteExistente);

	    return ResponseEntity.ok(actualizado);
	}

	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarCliente(@PathVariable Long id) {
	    Cliente cliente = clienteService.findById(id);

	    if (cliente == null) {
	        return ResponseEntity
	                .status(HttpStatus.NOT_FOUND)
	                .body("No existe un cliente con el ID: " + id);
	    }

	    clienteService.delete(id);

	    return ResponseEntity.ok("Cliente eliminado: " + cliente.getNombre());
	}

}