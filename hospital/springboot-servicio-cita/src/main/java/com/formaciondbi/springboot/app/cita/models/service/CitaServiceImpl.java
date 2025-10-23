package com.formaciondbi.springboot.app.cita.models.service;

import com.formaciondbi.springboot.app.cita.SpringbootServicioItemApplication;
import com.formaciondbi.springboot.app.cita.clientes.UsuarioClienteRest;
import com.formaciondbi.springboot.app.cita.models.Cita;
import com.formaciondbi.springboot.app.cita.models.Usuario;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.formaciondbi.springboot.app.cita.models.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CitaServiceImpl implements ICitaService {

    private final SpringbootServicioItemApplication springbootServicioItemApplication;

    @Autowired
    private CitaRepository citaRepo;

    @Autowired
    private UsuarioClienteRest usuarioCliente;

    CitaServiceImpl(SpringbootServicioItemApplication springbootServicioItemApplication) {
        this.springbootServicioItemApplication = springbootServicioItemApplication;
    }

    @Override
    public List<Cita> findAll() {
        return citaRepo.findAll()
                .stream()
                .map(c -> {
                    Usuario usuario = usuarioCliente.detalle(c.getUsuarioId());
                    c.setUsuario(usuario);
                    return c;
                })
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Cita> findToday() {
        LocalDate today = LocalDate.now();

        java.sql.Date startOfDay = java.sql.Date.valueOf(today);
        java.sql.Date endOfDay = java.sql.Date.valueOf(today);

        return citaRepo.findByFechaCitaBetween(startOfDay, endOfDay)
                .stream()
                .map(c -> {
                    Usuario usuario = usuarioCliente.detalle(c.getUsuarioId());
                    c.setUsuario(usuario);
                    return c;
                })
                .collect(Collectors.toList());
    }
    
    @Override
    public Cita findById(Long id) {
        Optional<Cita> citaOpt = citaRepo.findById(id);
        if (citaOpt.isPresent()) {
            Cita cita = citaOpt.get();
            Usuario usuario = usuarioCliente.detalle(cita.getUsuarioId());
            cita.setUsuario(usuario);
            return cita;
        }
        return null;
    }
    
    @HystrixCommand(fallbackMethod = "MetodoAlternativo")
    @Override
    public List<Cita> findByUser(Long usuarioId) {
        List<Cita> citas = citaRepo.findByUsuarioId(usuarioId);

        if (citas.isEmpty()) {
            return MetodoAlternativo(usuarioId);
        }

        return citas.stream()
                .map(c -> {
                    Usuario usuario = usuarioCliente.detalle(c.getUsuarioId());
                    c.setUsuario(usuario);
                    return c;
                })
                .collect(Collectors.toList());
    }


    public List<Cita> MetodoAlternativo(Long usuarioId) {
        Cita cita = new Cita();
        Usuario usuario = new Usuario();

        cita.setUsuarioId(usuarioId);
        cita.setMotivo("Servicio de usuarios no disponible. Esta es una cita por defecto.");
        usuario.setId(usuarioId);
        usuario.setNombre("Usuario desconocido");

        cita.setUsuario(usuario);

        return List.of(cita);
    }
    
    @Override
    public Cita create(Cita cita) {
        return citaRepo.save(cita);
    }

    @Override
    public Cita edit(Cita cita, Long id) {
        cita.setId(id);
        return citaRepo.save(cita);
    }

    @Override
    public void delete(Long id) {
        citaRepo.deleteById(id);
    }

	
}
