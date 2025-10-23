package com.formaciondbi.springboot.app.cita.models;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "fecha_cita")
    @Temporal(TemporalType.DATE)
    private Date fechaCita;

    private String motivo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Transient
    private Usuario usuario;
    
    public Cita() {
        this.createAt = new Date();
    }
    
    public Cita(Usuario usuario) {
        if (usuario != null) {
            this.usuario = usuario;
            this.usuarioId = usuario.getId();
        }
    }

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
