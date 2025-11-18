package com.formaciondbi.springboot.app.ventas.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "ventas")
public class Venta implements Serializable {
    private static final long serialVersionUID = 712674758254766650L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Double total; 
    
    private Long clienteId; 

    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    private Integer port;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "venta_id")
    private List<ItemVenta> items;

    @Transient
    private Cliente cliente; 
    
    public Venta() {
        this.items = new ArrayList<>();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
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
    
	public List<ItemVenta> getItems() {
		return items;
	}

	public void setItems(List<ItemVenta> items) {
		this.items = items;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
    
    public Double calcularTotal() {
        return this.items.stream()
            .mapToDouble(ItemVenta::getSubtotal)
            .sum();
    }
}