package com.formaciondbi.springboot.app.cita.models;

import java.util.Date;

public class Usuario {

    private Long id; 
    private String nombre;
    private int edad;
    private String correo;
    private String genero;
    private Date fechaNacimiento;
    private String nss;
    private Date createAt;
    private Integer port;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public Date getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(Date fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getNss() { return nss; }
    public void setNss(String nss) { this.nss = nss; }

    public Date getCreateAt() { return createAt; }
    public void setCreateAt(Date createAt) { this.createAt = createAt; }

    public Integer getPort() { return port; }
    public void setPort(Integer port) { this.port = port; }
}
