package com.vhre.planit.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Deshabilitar CSRF (común para APIs REST stateless con JWT o Basic Auth)
                .csrf(AbstractHttpConfigurer::disable)

                // Configuración de reglas de acceso a endpoints
                .authorizeHttpRequests(auth -> auth
                        // Permitir acceso público y sin autenticación a todas las rutas de Swagger/OpenAPI
                        .requestMatchers(
                                "/api-docs",
                                "/api-docs/**",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        // Cualquier otra ruta (como tu API de usuarios) requerirá autenticación
                        .anyRequest().authenticated()
                )

                // Habilita la autenticación básica (envío de credenciales en las cabeceras HTTP)
                .httpBasic(org.springframework.security.config.Customizer.withDefaults())

                // Mantiene el formulario web por defecto de Spring Security en el navegador
                .formLogin(form -> form.permitAll());

        return http.build();
    }
}
