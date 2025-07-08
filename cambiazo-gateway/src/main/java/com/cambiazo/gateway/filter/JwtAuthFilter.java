package com.cambiazo.gateway.filter;

import com.cambiazo.gateway.util.JwtUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class JwtAuthFilter implements GlobalFilter {

    private static final List<String> PUBLIC_PATHS = List.of(
            "/api/v2/products",
            "/api/v2/products/",
            "/api/v2/users",
            "/api/v2/users/",
            "/api/v2/authentication",
            "/api/v2/authentication/",
            "/api/v2/ongs/",
            "/api/v2/donations/ongs",
            "/api/v2/donations/category-ongs",
            "/api/v2/donations/projects/ongs",
            "/api/v2/donations/projects/ongs/**",
            "/api/v2/donations/social-networks/ongs",
            "/api/v2/donations/social-networks/ongs/**",
            "/api/v2/donations/account-number/ongs",
            "/api/v2/donations/account-number/ongs/**",
            "/api/v2/countries",
            "/api/v2/countries/**",
            "/api/v2/departments",
            "/api/v2/products/**",
            "/api/v2/products",
            "/api/v2/product-categories",
            "/api/v2/product-categories/**",
            "/api/v2/plans/**",
            "/api/v2/plans",
            "/api/v2/departments/**",
            "/api/v2/districts",
            "/api/v2/users",
            "/api/v2/users/**",
            "/api/v2/districts/**"
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        boolean isPublic = PUBLIC_PATHS.stream().anyMatch(path::startsWith);
        if (isPublic) {
            return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            try {
                String username = JwtUtil.extractUsername(jwt);
                if (JwtUtil.validateToken(jwt, username)) {
                    return chain.filter(exchange);
                }
            } catch (Exception e) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        }

        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}
