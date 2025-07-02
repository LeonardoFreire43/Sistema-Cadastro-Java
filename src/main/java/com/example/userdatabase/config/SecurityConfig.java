// Caminho do arquivo: com/example/userdatabase/config/SecurityConfig.java
package com.example.userdatabase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Import estático necessário para o .cors(withDefaults())
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Aplica a configuração de CORS que definimos em WebConfig
            .cors(withDefaults())
            
            // Desabilita a proteção CSRF, comum para APIs REST.
            .csrf(csrf -> csrf.disable())

            // Configura as regras de autorização.
            .authorizeHttpRequests(auth -> auth
                // Permite acesso público a todos os endpoints dentro de /api/users/
                .requestMatchers("/api/users/**").permitAll()
                
                // Exige autenticação para qualquer outra requisição.
                .anyRequest().authenticated()
            );

        return http.build();
    }
}
