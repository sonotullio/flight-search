package com.sonotullio.flightcomparator.flightsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient
public class FlightSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlightSearchApplication.class, args);
    }
}