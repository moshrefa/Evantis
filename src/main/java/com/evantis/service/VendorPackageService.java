package com.evantis.service;

import com.evantis.model.VendorPackage;
import com.evantis.repository.VendorPackageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VendorPackageService {

    private final VendorPackageRepository repository;

    public List<VendorPackage> findByVendorId(Long vendorId) {
        return repository.findByVendorIdAndActiveTrueOrderBySortOrderAsc(vendorId);
    }

    public VendorPackage save(VendorPackage pkg) {
        return repository.save(pkg);
    }
}
