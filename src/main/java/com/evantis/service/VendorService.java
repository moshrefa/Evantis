package com.evantis.service;

import com.evantis.model.Vendor;
import com.evantis.model.VendorCategory;
import com.evantis.repository.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Business logic for vendor management.
// Public methods serve the marketplace listing pages.
// Admin methods (save, deactivate) will be protected by auth when added.
@Service
@RequiredArgsConstructor
public class VendorService {

    private final VendorRepository vendorRepository;

    // ── Public / marketplace ────────────────────────────────────────────────

    // All active vendors (shown on homepage or browse page)
    public List<Vendor> findAllActive() {
        return vendorRepository.findByActiveTrue();
    }

    // Active vendors filtered by category (Photography or Event Decoration)
    public List<Vendor> findByCategory(VendorCategory category) {
        return vendorRepository.findByCategoryAndActiveTrue(category);
    }

    // Single vendor profile page
    public Optional<Vendor> findById(Long id) {
        return vendorRepository.findById(id);
    }

    // ── Admin ───────────────────────────────────────────────────────────────

    // Create or update a vendor listing
    public Vendor save(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    // Soft-delete: deactivate instead of removing from the database
    public void deactivate(Long id) {
        vendorRepository.findById(id).ifPresent(v -> {
            v.setActive(false);
            vendorRepository.save(v);
        });
    }

    // Hard delete (admin only — use with caution)
    public void deleteById(Long id) {
        vendorRepository.deleteById(id);
    }
}
