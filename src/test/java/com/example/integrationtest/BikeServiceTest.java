package com.example.integrationtest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BikeServiceTest {

    @Test
    void expectNull_WhenCalculatingEverageOfNoBikes() {
        // GIVEN
        BikeRepo bikeRepoMock = mock(BikeRepo.class);
        when(bikeRepoMock.getAllBikes()).thenReturn(Collections.emptySet());
        BikeService service = new BikeService(bikeRepoMock);

        // WHEN
        BigDecimal actual = service.calculateAverageBikePrice("red");

        // THEN
        Assertions.assertEquals(null, actual);
    }

    @Test
    void expectPrice_WhenCalculatingEverageOfOneBike() {
        // GIVEN
        BikeRepo bikeRepoMock = mock(BikeRepo.class);
        when(bikeRepoMock.getAllBikes()).thenReturn(Collections.singleton(
                new Bike(
                        "red",
                        new BigDecimal("350.49")
                )
        ));
        BikeService service = new BikeService(bikeRepoMock);

        // WHEN
        BigDecimal actual = service.calculateAverageBikePrice("red");

        // THEN
        Assertions.assertEquals(new BigDecimal("350.49"), actual);
    }

    @Test
    void expectNull_WhenCalculatingEverageOfBikesWithOtherColor() {
        // GIVEN
        BikeRepo bikeRepoMock = mock(BikeRepo.class);
        when(bikeRepoMock.getAllBikes()).thenReturn(Collections.singleton(
                new Bike(
                        "blue",
                        new BigDecimal("350.49")
                )
        ));
        BikeService service = new BikeService(bikeRepoMock);

        // WHEN
        BigDecimal actual = service.calculateAverageBikePrice("red");

        // THEN
        Assertions.assertEquals(null, actual);
    }

    @Test
    void expect50_WhenOneBikeCosts25AndTheOtherCosts75() {
        // GIVEN
        BikeRepo bikeRepoMock = mock(BikeRepo.class);
        when(bikeRepoMock.getAllBikes()).thenReturn(Set.of(
                new Bike(
                        "red",
                        new BigDecimal("25")
                ),
                new Bike(
                        "red",
                        new BigDecimal("75")
                )
        ));
        BikeService service = new BikeService(bikeRepoMock);

        // WHEN
        BigDecimal actual = service.calculateAverageBikePrice("red");

        // THEN
        Assertions.assertEquals(new BigDecimal(50), actual);
    }

}




