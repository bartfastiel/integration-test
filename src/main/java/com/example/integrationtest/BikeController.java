package com.example.integrationtest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/bikes")
@RequiredArgsConstructor
public class BikeController {

    private final BikeService bikeService;

    @PostMapping
    Bike createBike(@RequestBody Bike bike) {
        return bikeService.createBike(bike);
    }

    @GetMapping
    BigDecimal calculateAverageBikePrice(@RequestParam String color) {
        return bikeService.calculateAverageBikePrice(color);
    }
}
