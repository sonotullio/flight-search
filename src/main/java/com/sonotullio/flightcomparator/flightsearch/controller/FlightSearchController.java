package com.sonotullio.flightcomparator.flightsearch.controller;

import com.sonotullio.flightcomparator.common.model.Flight;
import com.sonotullio.flightcomparator.flightsearch.dto.FlightSearchRequest;
import com.sonotullio.flightcomparator.flightsearch.service.FlightSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flights")
@RequiredArgsConstructor
@Tag(name = "Flight Search", description = "API per la ricerca dei voli disponibili")
public class FlightSearchController {

    private final FlightSearchService flightSearchService;

    @PostMapping("/search")
    @Operation(
        summary = "Cerca voli disponibili",
        description = "Cerca voli disponibili in base ai criteri specificati come origine, destinazione, data e numero di passeggeri"
    )
    public ResponseEntity<List<Flight>> searchFlights(@Valid @RequestBody FlightSearchRequest request) {
        return ResponseEntity.ok(flightSearchService.searchFlights(request));
    }
}