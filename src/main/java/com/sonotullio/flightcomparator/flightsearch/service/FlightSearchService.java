package com.sonotullio.flightcomparator.flightsearch.service;

import com.sonotullio.flightcomparator.common.model.Flight;
import com.sonotullio.flightcomparator.flightsearch.client.ExternalFlightApiClient;
import com.sonotullio.flightcomparator.flightsearch.dto.FlightSearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightSearchService {

    private final ExternalFlightApiClient externalFlightApiClient;

    public List<Flight> searchFlights(FlightSearchRequest request) {
        List<Flight> flights = externalFlightApiClient.searchFlights(request);
        
        return flights.stream()
                .filter(flight -> flight.getAvailableSeats() >= request.getPassengers())
                .filter(flight -> request.getMaxPrice() == null || 
                        flight.getPrice().compareTo(request.getMaxPrice()) <= 0)
                .filter(flight -> request.getPreferredAirline() == null || 
                        flight.getAirline().equalsIgnoreCase(request.getPreferredAirline()))
                .filter(flight -> request.getCabinClass() == null || 
                        flight.getCabinClass().equalsIgnoreCase(request.getCabinClass()))
                .sorted(Comparator.comparing(Flight::getPrice))
                .collect(Collectors.toList());
    }
}