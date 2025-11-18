package com.formaciondbi.springboot.app.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class PreTiempoTranscurridoFilter implements GlobalFilter, Ordered {

    private static final Logger log = LoggerFactory.getLogger(PreTiempoTranscurridoFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {
        // Información del request (pre filter)
        String metodo = exchange.getRequest().getMethodValue();
        String url = exchange.getRequest().getURI().toString();
        log.info("{} request enrutado a {}", metodo, url);

        // Guardamos el tiempo de inicio
        long tiempoInicio = System.currentTimeMillis();
        exchange.getAttributes().put("tiempoInicio", tiempoInicio);

        // Continuamos con el flujo de la petición
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0; // se ejecuta antes que el filtro post
    }
}
