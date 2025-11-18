package com.formaciondbi.springboot.app.ventas.models;

import java.io.Serializable;
import java.util.Date;

public class Cliente implements Serializable {
	private static final long serialVersionUID = -6707141959397402627L;
	private long id;
	private String nombre;
	private int edad;
	private String email; 
	private Date createAt;
	private Integer port;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
}
