package com.evantis.service;

import com.evantis.dto.InquiryRequest;
import com.evantis.model.Inquiry;
import com.evantis.model.Vendor;
import com.evantis.repository.InquiryRepository;
import com.evantis.repository.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InquiryService {

    private final InquiryRepository inquiryRepository;
    private final VendorRepository vendorRepository;

    // Maps the inbound DTO to an entity, resolving vendorId to a Vendor if provided
    public Inquiry saveFromRequest(InquiryRequest req) {
        Vendor vendor = null;
        if (req.getVendorId() != null) {
            vendor = vendorRepository.findById(req.getVendorId()).orElse(null);
        }

        Inquiry inquiry = Inquiry.builder()
                .name(req.getName())
                .email(req.getEmail())
                .phone(req.getPhone())
                .eventType(req.getEventType())
                .eventDate(req.getEventDate())
                .guestCount(req.getGuestCount())
                .budget(req.getBudget())
                .location(req.getLocation())
                .message(req.getMessage())
                .vendor(vendor)
                .build();

        return inquiryRepository.save(inquiry);
    }

    public List<Inquiry> findAll() {
        return inquiryRepository.findAll();
    }

    public Optional<Inquiry> findById(Long id) {
        return inquiryRepository.findById(id);
    }

    public void deleteById(Long id) {
        inquiryRepository.deleteById(id);
    }
}
