package com.formaciondbi.springboot.app.ventas.models.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formaciondbi.springboot.app.ventas.clientes.ClienteRest;
import com.formaciondbi.springboot.app.ventas.clientes.ProductoRest;
import com.formaciondbi.springboot.app.ventas.models.repository.VentasRepository;
import com.formaciondbi.springboot.app.ventas.models.Cliente;
import com.formaciondbi.springboot.app.ventas.models.ItemVenta;
import com.formaciondbi.springboot.app.ventas.models.Producto;
import com.formaciondbi.springboot.app.ventas.models.Venta;

@Service("ventasServiceFeign")
@Primary
public class VentasServiceFeign implements VentasService {

    @Autowired
    private ClienteRest clienteFeign;

    @Autowired
    private ProductoRest productoFeign;

    @Autowired
    private VentasRepository ventasRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Venta> findAll() {
        return ventasRepository.findAll().stream()
                .map(this::enriquecerVenta)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Venta findById(Long id) {
        Venta venta = ventasRepository.findById(id).orElse(null);
        return enriquecerVenta(venta);
    }

    @Override
    @Transactional
    public Venta save(Venta venta) {
        venta.setCreateAt(new Date());

        calcularTotalesVenta(venta);

        Venta ventaGuardada = ventasRepository.save(venta);

        return enriquecerVenta(ventaGuardada);
    }

    @Override
    @Transactional
    public Venta update(Long id, Venta venta) {
        Optional<Venta> optExisting = ventasRepository.findById(id);
        
        if (optExisting.isPresent()) {
            Venta existing = optExisting.get();
            
            existing.setClienteId(venta.getClienteId());
            existing.getItems().clear();
            existing.getItems().addAll(venta.getItems());

            calcularTotalesVenta(existing);

            Venta ventaActualizada = ventasRepository.save(existing);

            return enriquecerVenta(ventaActualizada);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        ventasRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Venta> findByClienteId(Long clienteId) {
        return ventasRepository.findByClienteId(clienteId).stream()
                .map(this::enriquecerVenta)
                .collect(Collectors.toList());
    }

    private void calcularTotalesVenta(Venta venta) {
        if (venta.getItems() ==null) {
            venta.setTotal(0.0);
            return;
        }

        venta.getItems().forEach(item -> {
            try {
                Producto producto = productoFeign.detalle(item.getProductoId());

                if (producto != null) {
                    item.setPrecioUnitario(producto.getPrecio());
                    item.setSubtotal(item.getCantidad() * item.getPrecioUnitario());
                } else {
                    item.setPrecioUnitario(0.0);
                    item.setSubtotal(0.0);
                }
            } catch (Exception e) {
                item.setPrecioUnitario(0.0);
                item.setSubtotal(0.0);
            }
        });

        venta.setTotal(venta.calcularTotal());
    }

    private Venta enriquecerVenta(Venta venta) {
        if (venta == null) {
            return null;
        }

        try {
            Cliente cliente = clienteFeign.detalle(venta.getClienteId());
            venta.setCliente(cliente);
        } catch (Exception e) {
            venta.setCliente(null);
        }

        if (venta.getItems() != null) {
            venta.getItems().forEach(item -> {
                try {
                    Producto producto = productoFeign.detalle(item.getProductoId());
                    item.setProducto(producto);
                } catch (Exception e) {
                    item.setProducto(null);
                }
            });
        }
        return venta;
    }
}