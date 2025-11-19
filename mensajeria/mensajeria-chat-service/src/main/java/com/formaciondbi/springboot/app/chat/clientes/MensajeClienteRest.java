package com.formaciondbi.springboot.app.chat.clientes;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import com.formaciondbi.springboot.app.chat.models.Mensaje;

@FeignClient(name = "servicio-mensajes")
public interface MensajeClienteRest {

    @GetMapping("/listar")
    public List<Mensaje> listar();
    
}