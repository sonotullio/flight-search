package com.sonotullio.flightcomparator.flightsearch.dto;

import lombok.Builder;
import lombok.Data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class FlightSearchRequest {
    @NotNull(message = "Origin airport code is required")
    private String origin;
    
    @NotNull(message = "Destination airport code is required")
    private String destination;
    
    @NotNull(message = "Departure date is required")
    private LocalDateTime departureDate;
    
    @Min(value = 1, message = "Number of passengers must be at least 1")
    private Integer passengers;
    
    private String cabinClass;
    private BigDecimal maxPrice;
    private String preferredAirline;
}