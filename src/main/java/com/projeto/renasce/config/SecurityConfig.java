package com.projeto.renasce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desabilitado para facilitar testes via Postman
            .authorizeHttpRequests(auth -> auth
                // Qualquer um pode ver as atletas
                .requestMatchers(HttpMethod.GET, "/atletas/**").permitAll()
                // Apenas ADMIN pode cadastrar ou alterar
                .requestMatchers(HttpMethod.POST, "/atletas/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/atletas/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/atletas/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults()); // Ativa a janelinha de login do navegador/Postman

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Criando um usuário Admin em memória para o seu teste
        UserDetails admin = User.builder()
                .username("giovanni")
                .password("{noop}renasce123") // {noop} indica que a senha está em texto plano (não faça isso em produção!)
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }
}