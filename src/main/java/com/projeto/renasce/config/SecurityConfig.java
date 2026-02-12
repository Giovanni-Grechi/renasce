package com.projeto.renasce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Ativa a configuração de CORS definida abaixo
            .cors(Customizer.withDefaults()) 
            
            // Desativa CSRF, necessário para APIs que recebem POST de domínios externos
            .csrf(csrf -> csrf.disable())    
            
            // Define as regras de autorização
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()    // Permite acesso sem login para testes
            );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Permite que qualquer origem acesse a API (essencial para o localhost e Netlify)
        configuration.setAllowedOriginPatterns(Arrays.asList("*")); 
        
        // Define quais métodos HTTP são permitidos no sistema da Gauro
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        
        // Define os cabeçalhos permitidos (essencial para o Axios)
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept"));
        
        // Permite o envio de cookies/autenticação se necessário no futuro
        configuration.setAllowCredentials(true);

        // Aplica essa configuração em todas as rotas da API
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }
}