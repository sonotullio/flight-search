package com.sonotullio.flightcomparator.flightsearch.client;

import com.sonotullio.flightcomparator.common.model.Flight;
import com.sonotullio.flightcomparator.flightsearch.dto.FlightSearchRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Component
public class ExternalFlightApiClient {
    
    private final Random random = new Random();
    
    public List<Flight> searchFlights(FlightSearchRequest request) {
        // Mock implementation for demonstration
        // In a real scenario, this would call external airline APIs
        return List.of(
            createMockFlight(request, "Alitalia", "AZ"),
            createMockFlight(request, "Lufthansa", "LH"),
            createMockFlight(request, "Air France", "AF")
        );
    }
    
    private Flight createMockFlight(FlightSearchRequest request, String airline, String airlineCode) {
        LocalDateTime departure = request.getDepartureDate();
        return Flight.builder()
                .id(airlineCode + random.nextInt(1000))
                .airline(airline)
                .flightNumber(airlineCode + random.nextInt(9999))
                .origin(request.getOrigin())
                .destination(request.getDestination())
                .departureTime(departure)
                .arrivalTime(departure.plusHours(1 + random.nextInt(3)))
                .price(new BigDecimal(100 + random.nextInt(500)))
                .currency("EUR")
                .availableSeats(10 + random.nextInt(100))
                .cabinClass(request.getCabinClass() != null ? request.getCabinClass() : "ECONOMY")
                .build();
    }
}