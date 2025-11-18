package com.formaciondbi.springboot.app.ventas.models;

import java.io.Serializable;
import java.util.Date;

public class Producto implements Serializable {
	private static final long serialVersionUID = 1159404504017828780L;
	private long id;
	private String nombre;
	private double precio;
	private int stock;
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
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public double getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
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