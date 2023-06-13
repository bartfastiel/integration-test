package com.example.integrationtest;

import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository
public class BikeRepo {

    private final Set<Bike> bikes = new HashSet<>();

    public Bike createBike(Bike bike) {
        bikes.add(bike);
        return bike;
    }

    public Set<Bike> getAllBikes() {
        return Collections.unmodifiableSet(bikes);
    }
}
