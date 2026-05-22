package com.evantis.repository;

import com.evantis.model.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Data access layer for Inquiry records.
// JpaRepository provides save, findById, findAll, delete out of the box.
// Add custom query methods here as the business grows
// (e.g. find by event type, find by date range, find unread inquiries).
@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

    // Find all inquiries from a specific email address
    List<Inquiry> findByEmail(String email);

    // Find all inquiries for a specific event type (case-sensitive)
    List<Inquiry> findByEventType(String eventType);
}
