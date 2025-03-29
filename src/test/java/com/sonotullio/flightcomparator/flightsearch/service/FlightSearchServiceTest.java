package com.sonotullio.flightcomparator.flightsearch.service;

import com.sonotullio.flightcomparator.common.model.Flight;
import com.sonotullio.flightcomparator.flightsearch.client.ExternalFlightApiClient;
import com.sonotullio.flightcomparator.flightsearch.dto.FlightSearchRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FlightSearchServiceTest {

    private FlightSearchService flightSearchService;

    @Mock
    private ExternalFlightApiClient externalFlightApiClient;

    @BeforeEach
    void setUp() {
        flightSearchService = new FlightSearchService(externalFlightApiClient);
    }

    @Test
    void shouldReturnAvailableFlights() {
        // Given
        FlightSearchRequest request = FlightSearchRequest.builder()
                .origin("MXP")
                .destination("FCO")
                .departureDate(LocalDateTime.now().plusDays(1))
                .passengers(2)
                .build();

        Flight mockFlight = Flight.builder()
                .id("1")
                .airline("Alitalia")
                .flightNumber("AZ1234")
                .origin("MXP")
                .destination("FCO")
                .departureTime(LocalDateTime.now().plusDays(1))
                .arrivalTime(LocalDateTime.now().plusDays(1).plusHours(1))
                .price(new BigDecimal("150.00"))
                .currency("EUR")
                .availableSeats(10)
                .cabinClass("ECONOMY")
                .build();

        when(externalFlightApiClient.searchFlights(any())).thenReturn(List.of(mockFlight));

        // When
        List<Flight> result = flightSearchService.searchFlights(request);

        // Then
        assertThat(result).isNotEmpty()
                .hasSize(1)
                .first()
                .satisfies(flight -> {
                    assertThat(flight.getOrigin()).isEqualTo(request.getOrigin());
                    assertThat(flight.getDestination()).isEqualTo(request.getDestination());
                    assertThat(flight.getAvailableSeats()).isGreaterThanOrEqualTo(request.getPassengers());
                });
    }
}