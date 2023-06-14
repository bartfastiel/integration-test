package com.example.integrationtest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Set;

@SpringBootTest
@AutoConfigureMockMvc
class BikeIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BikeRepo bikeRepo;

    @Test
    @DirtiesContext
    void expect50_WhenOneBikeCosts25AndTheOtherCosts75() throws Exception {
        // GIVEN
        bikeRepo.createBike(new Bike("red", new BigDecimal("25")));
        bikeRepo.createBike(new Bike("red", new BigDecimal("75")));

        // WHEN
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/bikes?color=red")
                )

                // THEN
                .andExpect(
                        MockMvcResultMatchers.content().string("50")
                );
    }

    @Test
    @DirtiesContext
    void expectABike_WhenStoringANewBikeViaPost() throws Exception {
        // GIVEN
        // WHEN
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/bikes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "color": "red",
                                    "price": "12.34"
                                }
                                """)
        );

        // THEN
        Set<Bike> allBikes = bikeRepo.getAllBikes();
        Assertions.assertThat(allBikes).containsExactly(
                new Bike("red", new BigDecimal("12.34"))
        );
    }

}
