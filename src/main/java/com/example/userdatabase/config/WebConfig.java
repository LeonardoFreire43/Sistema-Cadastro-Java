// Caminho do arquivo: com/example/userdatabase/config/WebConfig.java
package com.example.userdatabase.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") 
                // Permite requisições do front-end.
                .allowedOrigins("http://127.0.0.1:5500", "http://localhost:5500") 
                // Define quais métodos HTTP são permitidos.
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") 
                // Permite que todos os cabeçalhos sejam enviados.
                .allowedHeaders("*") 
                .allowCredentials(true);
    }
}
