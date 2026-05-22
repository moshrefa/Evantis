package com.evantis;

import com.evantis.model.Vendor;
import com.evantis.model.VendorCategory;
import com.evantis.model.VendorPackage;
import com.evantis.repository.VendorPackageRepository;
import com.evantis.repository.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final VendorRepository vendorRepository;
    private final VendorPackageRepository packageRepository;

    @Override
    public void run(String... args) {
        removeVendors("Photoflick", "Fattha's Event Management");
        if (vendorRepository.count() == 0) {
            seedVendors();
        }
        applyProfileImages();
    }

    private void removeVendors(String... brandNames) {
        java.util.Set<String> toRemove = new java.util.HashSet<>(java.util.Arrays.asList(brandNames));
        vendorRepository.findAll().stream()
            .filter(v -> toRemove.contains(v.getBrandName()))
            .forEach(v -> {
                packageRepository.deleteAll(
                    packageRepository.findByVendorIdAndActiveTrueOrderBySortOrderAsc(v.getId())
                );
                vendorRepository.delete(v);
            });
    }

    private void applyProfileImages() {
        java.util.Map<String, String> images = java.util.Map.of(
            "Shots by Mo",               "assets/Shots.jpg",
            "Balloons and Blooms by Mo", "assets/Balloons.jpg"
        );
        vendorRepository.findAll().forEach(v -> {
            String expected = images.get(v.getBrandName());
            if (expected != null && !expected.equals(v.getProfileImageUrl())) {
                v.setProfileImageUrl(expected);
                vendorRepository.save(v);
            }
        });
    }

    private void seedVendors() {

        // ── Shots by Mo ────────────────────────────────────────────────────────
        Vendor shotsByMo = vendorRepository.save(Vendor.builder()
                .name("Moshrefa Chaity")
                .brandName("Shots by Mo")
                .category(VendorCategory.PHOTOGRAPHY)
                .bio("Capturing life's most cherished moments with a timeless, editorial style. "
                        + "Based in Greater Austin, TX — specializing in weddings, family milestones, "
                        + "and corporate events with warmth and authenticity.")
                .location("Greater Austin, TX")
                .email("hello@shotsbymo.com")
                .build());

        packageRepository.saveAll(List.of(
                VendorPackage.builder().vendor(shotsByMo).sortOrder(1)
                        .name("Essential").priceLabel("$750").price(new BigDecimal("750"))
                        .description("Perfect for intimate celebrations and short events.")
                        .features(List.of(
                                "4 hours of coverage",
                                "1 photographer",
                                "200 edited digital photos",
                                "Online gallery delivery"))
                        .build(),
                VendorPackage.builder().vendor(shotsByMo).sortOrder(2)
                        .name("Standard").priceLabel("$1,400").price(new BigDecimal("1400"))
                        .description("Our most popular package for weddings and full-day events.")
                        .features(List.of(
                                "8 hours of coverage",
                                "1 photographer",
                                "400 edited digital photos",
                                "Online gallery",
                                "Print release included"))
                        .build(),
                VendorPackage.builder().vendor(shotsByMo).sortOrder(3)
                        .name("All Day").priceLabel("$2,200").price(new BigDecimal("2200"))
                        .description("Complete all-day coverage for your most important day.")
                        .features(List.of(
                                "10 hours of coverage",
                                "2 photographers",
                                "600+ edited digital photos",
                                "Online gallery",
                                "10×10 photo album",
                                "Engagement session included"))
                        .build()
        ));

        // ── Photoflick ──────────────────────────────────────────────────────────
        // Vendor photoflick = vendorRepository.save(Vendor.builder()
        //         .name("Photoflick Studio")
        //         .brandName("Photoflick")
        //         .category(VendorCategory.PHOTOGRAPHY)
        //         .bio("Dhaka-based photography studio with a passion for vibrant storytelling. "
        //                 + "We cover weddings, holud ceremonies, corporate events, and portraits "
        //                 + "with a bold, colorful aesthetic.")
        //         .location("Dhaka, Bangladesh")
        //         .email("photoflick.bd@gmail.com")
        //         .build());

        // packageRepository.saveAll(List.of(
        //         VendorPackage.builder().vendor(photoflick).sortOrder(1)
        //                 .name("Basic").priceLabel("৳12,000").price(new BigDecimal("12000"))
        //                 .description("Ideal for small gatherings and short ceremonies.")
        //                 .features(List.of(
        //                         "3 hours of coverage",
        //                         "1 photographer",
        //                         "150 edited digital photos",
        //                         "Digital delivery"))
        //                 .build(),
        //         VendorPackage.builder().vendor(photoflick).sortOrder(2)
        //                 .name("Standard").priceLabel("৳22,000").price(new BigDecimal("22000"))
        //                 .description("Full event coverage for weddings and cultural ceremonies.")
        //                 .features(List.of(
        //                         "6 hours of coverage",
        //                         "1 photographer",
        //                         "300 edited digital photos",
        //                         "Same-day preview (10 photos)",
        //                         "USB delivery"))
        //                 .build(),
        //         VendorPackage.builder().vendor(photoflick).sortOrder(3)
        //                 .name("Premium").priceLabel("৳40,000").price(new BigDecimal("40000"))
        //                 .description("Our complete package for grand celebrations.")
        //                 .features(List.of(
        //                         "Full day coverage",
        //                         "2 photographers",
        //                         "500+ edited digital photos",
        //                         "Photo album (50 pages)",
        //                         "Highlight reel",
        //                         "USB + online gallery"))
        //                 .build()
        // ));

        // ── Balloons and Blooms by Mo ───────────────────────────────────────────
        Vendor balloomsByMo = vendorRepository.save(Vendor.builder()
                .name("Moshrefa Chaity")
                .brandName("Balloons and Blooms by Mo")
                .category(VendorCategory.EVENT_DECORATION)
                .bio("Bringing joy and beauty to every celebration in the Greater Austin area. "
                        + "Specializing in custom balloon installations, floral arrangements, "
                        + "and full venue styling for birthdays, weddings, showers, and corporate events.")
                .location("Greater Austin, TX")
                .email("hello@balloonsandblooms.com")
                .build());

        packageRepository.saveAll(List.of(
                VendorPackage.builder().vendor(balloomsByMo).sortOrder(1)
                        .name("Intimate").priceLabel("$600").price(new BigDecimal("600"))
                        .description("Charming décor for small, intimate gatherings.")
                        .features(List.of(
                                "1 balloon arch or column",
                                "Table centerpieces (up to 5 tables)",
                                "Color-coordinated theme",
                                "Setup & teardown"))
                        .build(),
                VendorPackage.builder().vendor(balloomsByMo).sortOrder(2)
                        .name("Classic").priceLabel("$1,200").price(new BigDecimal("1200"))
                        .description("Elegant styling for mid-size events and celebrations.")
                        .features(List.of(
                                "Full venue balloon installations",
                                "Floral accent pieces",
                                "Backdrop or feature wall",
                                "Ambient lighting",
                                "Setup & teardown"))
                        .build(),
                VendorPackage.builder().vendor(balloomsByMo).sortOrder(3)
                        .name("Luxe").priceLabel("$2,500").price(new BigDecimal("2500"))
                        .description("A complete luxury transformation for your dream event.")
                        .features(List.of(
                                "Custom concept & mood board",
                                "Premium floral installations",
                                "Full draping & fabric décor",
                                "Statement balloon ceiling or arch",
                                "Lighting design",
                                "Day-of coordination"))
                        .build()
        ));

        // ── Fattha's Event Management ──────────────────────────────────────────
        // Vendor fattha = vendorRepository.save(Vendor.builder()
        //         .name("Fattha's Event Management")
        //         .brandName("Fattha's Event Management")
        //         .category(VendorCategory.EVENT_DECORATION)
        //         .bio("One of Bangladesh's premier event management companies, creating breathtaking "
        //                 + "celebrations since 2015. From intimate gaye holud ceremonies to grand wedding "
        //                 + "receptions, we handle every detail with elegance and precision.")
        //         .location("Dhaka, Bangladesh")
        //         .email("fattha.events@gmail.com")
        //         .build());

        // packageRepository.saveAll(List.of(
        //         VendorPackage.builder().vendor(fattha).sortOrder(1)
        //                 .name("Basic").priceLabel("৳15,000").price(new BigDecimal("15000"))
        //                 .description("Essential decoration for smaller gatherings.")
        //                 .features(List.of(
        //                         "Stage decoration",
        //                         "Backdrop & banner",
        //                         "Basic floral arrangements",
        //                         "Table centerpieces (up to 8)"))
        //                 .build(),
        //         VendorPackage.builder().vendor(fattha).sortOrder(2)
        //                 .name("Standard").priceLabel("৳30,000").price(new BigDecimal("30000"))
        //                 .description("Full venue decoration for weddings and cultural events.")
        //                 .features(List.of(
        //                         "Complete stage & backdrop",
        //                         "Seating area décor",
        //                         "Ambient & uplighting",
        //                         "Floral gate & entrance décor",
        //                         "Coordination team on site"))
        //                 .build(),
        //         VendorPackage.builder().vendor(fattha).sortOrder(3)
        //                 .name("Premium").priceLabel("৳60,000").price(new BigDecimal("60000"))
        //                 .description("A full transformation for grand celebrations.")
        //                 .features(List.of(
        //                         "Custom theme concept & design",
        //                         "Premium florals & draping",
        //                         "Full venue transformation",
        //                         "Chandelier & statement lighting",
        //                         "Holud / wedding ceremony sets",
        //                         "Full coordination & management"))
        //                 .build()
        // ));
    }
}
