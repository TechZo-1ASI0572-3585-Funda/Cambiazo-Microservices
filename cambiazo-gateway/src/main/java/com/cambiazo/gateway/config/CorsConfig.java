package com.cambiazo.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:4200"); // Origen permitido
        config.addAllowedMethod("*"); // Métodos permitidos (GET, POST, etc.)
        config.addAllowedHeader("*"); // Headers permitidos
        config.setAllowCredentials(true); // Permitir credenciales (si es necesario)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Aplica CORS a todas las rutas

        return new CorsFilter(source);
    }
}

