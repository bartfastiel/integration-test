package com.example.integrationtest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BikeService {

    private final BikeRepo bikeRepo;

    public Bike createBike(Bike bike) {
        return bikeRepo.createBike(bike);
    }
}
