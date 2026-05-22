package com.evantis;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Entry point for the Evantis backend API.
// Loads .env file before Spring starts so application.properties can read
// environment variables via ${VAR_NAME} placeholders.
@SpringBootApplication
public class EvantisApplication {

    public static void main(String[] args) {
        // Load .env file if present (local dev only).
        // In production, real environment variables take precedence.
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()  // don't crash if .env doesn't exist (e.g. in CI/prod)
                .load();

        dotenv.entries().forEach(entry ->
                System.setProperty(entry.getKey(), entry.getValue())
        );

        SpringApplication.run(EvantisApplication.class, args);
    }
}
