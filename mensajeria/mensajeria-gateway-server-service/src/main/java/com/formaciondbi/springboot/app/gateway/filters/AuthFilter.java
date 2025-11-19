package com.formaciondbi.springboot.app.gateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String tokenHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

        System.out.println("Validando petición a: " + exchange.getRequest().getPath());

        if (tokenHeader != null && tokenHeader.equals("TilinToken")) {
            return chain.filter(exchange);
        }

        System.out.println("Token inválido.");
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return -1;
    }
}