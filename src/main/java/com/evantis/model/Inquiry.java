package com.evantis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

// Represents a client inquiry submitted through the Evantis contact/booking form.
// Stored in the "inquiries" table in PostgreSQL.
// This is the primary lead capture model for the business.
@Entity
@Table(name = "inquiries")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Client contact details
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String phone;

    // Event details
    @Column(name = "event_type")
    private String eventType;         // e.g. Wedding, Corporate, Birthday, etc.

    @Column(name = "event_date")
    private LocalDate eventDate;

    @Column(name = "guest_count")
    private Integer guestCount;

    // Budget as decimal to support currency values precisely
    private BigDecimal budget;

    private String location;

    // Free-text message from the client
    @Column(columnDefinition = "TEXT")
    private String message;

    // Optional: which vendor this inquiry is directed at.
    // Null means the client is inquiring about Evantis generally (not a specific vendor).
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    // Automatically set when the inquiry is first saved
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
