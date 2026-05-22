package com.evantis.controller;

import com.evantis.model.Vendor;
import com.evantis.model.VendorCategory;
import com.evantis.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Public REST endpoints for the Evantis vendor marketplace.
//
// GET  /api/vendors                          → all active vendors
// GET  /api/vendors?category=PHOTOGRAPHY     → active photographers
// GET  /api/vendors?category=EVENT_DECORATION → active decorators
// GET  /api/vendors/{id}                     → single vendor profile
// POST /api/vendors                          → create vendor (admin — add auth later)
@RestController
@RequestMapping("/api/vendors")
@RequiredArgsConstructor
public class VendorController {

    private final VendorService vendorService;

    // List vendors — optionally filtered by category via query param
    // e.g. GET /api/vendors?category=PHOTOGRAPHY
    @GetMapping
    public ResponseEntity<List<Vendor>> list(
            @RequestParam(required = false) VendorCategory category) {

        List<Vendor> vendors = (category != null)
                ? vendorService.findByCategory(category)
                : vendorService.findAllActive();

        return ResponseEntity.ok(vendors);
    }

    // Single vendor profile
    @GetMapping("/{id}")
    public ResponseEntity<Vendor> getById(@PathVariable Long id) {
        return vendorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new vendor listing — will be secured by admin auth in a future step
    @PostMapping
    public ResponseEntity<Vendor> create(@RequestBody Vendor vendor) {
        return ResponseEntity.ok(vendorService.save(vendor));
    }

    // Deactivate (soft delete) a vendor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        vendorService.deactivate(id);
        return ResponseEntity.noContent().build();
    }
}
