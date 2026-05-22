package com.evantis.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

// A vendor is a photographer or event decorator listed on the Evantis platform.
// Vendors belong to one category and can have their own brand name separate
// from their personal name (e.g. name="Jane Smith", brandName="Lumière Studio").
@Entity
@Table(name = "vendors")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Legal / personal name of the photographer or decorator
    @Column(nullable = false)
    private String name;

    // Business or brand name (optional — some vendors operate under a brand)
    @Column(name = "brand_name")
    private String brandName;

    // PHOTOGRAPHY or EVENT_DECORATION
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VendorCategory category;

    // Short bio shown on the vendor profile card
    @Column(columnDefinition = "TEXT")
    private String bio;

    private String location;
    private String email;
    private String phone;

    @Column(name = "website_url")
    private String websiteUrl;

    // URL of the vendor's profile/cover image
    @Column(name = "profile_image_url")
    private String profileImageUrl;

    // Controls whether the vendor appears in public listings
    @Column(nullable = false)
    @Builder.Default
    private boolean active = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // Convenience: returns brandName if set, otherwise falls back to name
    @Transient
    public String getDisplayName() {
        return (brandName != null && !brandName.isBlank()) ? brandName : name;
    }
}
