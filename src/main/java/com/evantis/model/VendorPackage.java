package com.evantis.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "vendor_packages")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendorPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "price_label")
    private String priceLabel;

    private BigDecimal price;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "vendor_package_features",
                     joinColumns = @JoinColumn(name = "package_id"))
    @Column(name = "feature")
    @OrderColumn(name = "feature_order")
    private List<String> features;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Builder.Default
    @Column(nullable = false)
    private boolean active = true;
}
