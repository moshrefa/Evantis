package com.evantis.repository;

import com.evantis.model.Vendor;
import com.evantis.model.VendorCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Database access for vendors.
// Supports filtering by category and active status — the two most common
// queries needed by the public-facing marketplace listing pages.
@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

    // All active vendors in a category (used for public listing pages)
    List<Vendor> findByCategoryAndActiveTrue(VendorCategory category);

    // All vendors in a category regardless of active status (used for admin)
    List<Vendor> findByCategory(VendorCategory category);

    // All active vendors across all categories
    List<Vendor> findByActiveTrue();
}
