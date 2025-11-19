package com.formaciondbi.springboot.app.chat.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formaciondbi.springboot.app.chat.clientes.MensajeClienteRest;
import com.formaciondbi.springboot.app.chat.clientes.UsuarioClienteRest;
import com.formaciondbi.springboot.app.chat.models.Mensaje;
import com.formaciondbi.springboot.app.chat.models.Usuario;

@RestController
public class ChatController {

    @Autowired
    private MensajeClienteRest mensajeCliente;

    @Autowired
    private UsuarioClienteRest usuarioCliente;

    @GetMapping("/dialogo")
    public List<String> obtenerChatCompleto() {
        List<Mensaje> mensajes = mensajeCliente.listar();
        
        List<String> chatFormateado = new ArrayList<>();

        for (Mensaje msg : mensajes) {
            Usuario usuario = usuarioCliente.detalle(msg.getUsuarioId());
            
            String linea = "Usuario " + usuario.getNombre() + ": " + msg.getTexto();
            
            chatFormateado.add(linea);
        }

        return chatFormateado;
    }
}