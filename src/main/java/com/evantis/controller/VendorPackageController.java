package com.evantis.controller;

import com.evantis.model.VendorPackage;
import com.evantis.service.VendorPackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// GET /api/vendors/{vendorId}/packages  → all active packages for a vendor
@RestController
@RequestMapping("/api/vendors/{vendorId}/packages")
@RequiredArgsConstructor
public class VendorPackageController {

    private final VendorPackageService packageService;

    @GetMapping
    public ResponseEntity<List<VendorPackage>> list(@PathVariable Long vendorId) {
        return ResponseEntity.ok(packageService.findByVendorId(vendorId));
    }
}
