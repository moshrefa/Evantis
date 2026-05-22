package com.evantis.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

// Decouples the REST request body from the JPA entity.
// vendorId is optional — null means a general Evantis inquiry.
@Data
public class InquiryRequest {

    private String name;
    private String email;
    private String phone;
    private String eventType;
    private LocalDate eventDate;
    private Integer guestCount;
    private BigDecimal budget;
    private String location;
    private String message;
    private Long vendorId;
}
