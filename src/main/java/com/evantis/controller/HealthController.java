package com.evantis.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Simple health check endpoint.
// Used to verify the API is running — useful for deployment checks,
// load balancers, and frontend connection testing.
@RestController
@RequestMapping("/api")
public class HealthController {

    // GET /api/health
    // Returns a plain confirmation that the Evantis API is live.
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Evantis API running");
    }
}
