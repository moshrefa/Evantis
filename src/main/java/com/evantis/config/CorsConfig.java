package com.evantis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Configures which frontend origins are allowed to call this API.
// Reads FRONTEND_URL from environment for the deployed frontend domain.
// localhost:3000 and localhost:5500 are always allowed for local development.
@Configuration
public class CorsConfig {

    @Value("${app.frontend-url:http://localhost:3000}")
    private String frontendUrl;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins(
                                "http://localhost:3000",   // React dev server
                                "http://localhost:5500",   // Live Server (VS Code)
                                "http://127.0.0.1:5500",  // Live Server alternate
                                "https://evantis.com",    // production domain
                                "https://www.evantis.com",
                                frontendUrl               // from FRONTEND_URL env var
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
