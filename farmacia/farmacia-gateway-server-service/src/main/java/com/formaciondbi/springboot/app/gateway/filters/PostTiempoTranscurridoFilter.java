package com.formaciondbi.springboot.app.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class PostTiempoTranscurridoFilter implements GlobalFilter, Ordered {

    private static final Logger log = LoggerFactory.getLogger(PostTiempoTranscurridoFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {
        log.info("Ejecutando pre filter...");

        // Guardamos el tiempo de inicio
        long tiempoInicio = System.currentTimeMillis();
        exchange.getAttributes().put("tiempoInicio", tiempoInicio);

        // Continuamos con la cadena y luego ejecutamos el post-filter
        return chain.filter(exchange).then(
            Mono.fromRunnable(() -> {
                Long inicio = exchange.getAttribute("tiempoInicio");
                if (inicio != null) {
                    long tiempoFinal = System.currentTimeMillis();
                    long tiempoTranscurrido = tiempoFinal - inicio;

                    log.info(String.format("Tiempo transcurrido en segundos: %.2f seg", tiempoTranscurrido / 1000.0));
                    log.info(String.format("Tiempo transcurrido en milisegundos: %d ms", tiempoTranscurrido));
                }
            })
        );
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
