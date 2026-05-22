package com.evantis.controller;

import com.evantis.dto.InquiryRequest;
import com.evantis.model.Inquiry;
import com.evantis.service.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// REST endpoints for client inquiries submitted through the Evantis contact form.
//
// POST   /api/inquiries          → submit a new inquiry (public)
// GET    /api/inquiries          → list all inquiries   (admin — add auth later)
// GET    /api/inquiries/{id}     → single inquiry       (admin)
// DELETE /api/inquiries/{id}     → remove inquiry       (admin)
@RestController
@RequestMapping("/api/inquiries")
@RequiredArgsConstructor
public class InquiryController {

    private final InquiryService inquiryService;

    // Contact/booking form submission — returns a plain message so the frontend
    // can display it directly without parsing a full entity
    @PostMapping
    public ResponseEntity<Map<String, String>> submit(@RequestBody InquiryRequest request) {
        inquiryService.saveFromRequest(request);
        return ResponseEntity.ok(Map.of("message",
                "Thank you, " + request.getName() + "! We'll be in touch shortly."));
    }

    @GetMapping
    public ResponseEntity<List<Inquiry>> listAll() {
        return ResponseEntity.ok(inquiryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inquiry> getById(@PathVariable Long id) {
        return inquiryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        inquiryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
