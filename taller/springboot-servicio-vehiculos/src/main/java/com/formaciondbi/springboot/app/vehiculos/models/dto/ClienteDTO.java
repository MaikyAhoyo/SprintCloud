package com.formaciondbi.springboot.app.vehiculos.models.dto;

import java.util.Date;

public class ClienteDTO {

    private long id;
    private String nombre;
    private int edad;
    private String email;
    private Date createAt;

    public ClienteDTO() {
    }

    public ClienteDTO(long id, String nombre, int edad, String email, Date createAt) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.email = email;
        this.createAt = createAt;
    }

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

    @Override
    public String toString() {
        return "ClienteDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", email='" + email + '\'' +
                ", createAt=" + createAt +
                '}';
    }
}
