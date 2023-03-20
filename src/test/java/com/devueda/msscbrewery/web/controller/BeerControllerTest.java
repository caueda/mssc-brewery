package com.devueda.msscbrewery.web.controller;

import com.devueda.msscbrewery.services.BeerService;
import com.devueda.msscbrewery.web.model.BeerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    private static final String BEER_V1_PATH="/api/v1/beer";
    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        this.objectMapper = new ObjectMapper();
    }

    @MockBean
    BeerService beerService;

    @Test
    void getBeerById() throws Exception {
        mockMvc.perform(get(BEER_V1_PATH + "/{beerId}", UUID.randomUUID()))
                .andExpect(status().isOk());
    }

    @Test
    void handlePost() throws Exception {
        var beerDto = BeerDto.builder()
                .beerName("BHRAMA")
                .beerStyle("PALE ALE")
                .upc(1000L)
                .build();
        when(beerService.saveNewBeer(Mockito.isA(BeerDto.class))).thenReturn(BeerDto.builder().id(UUID.randomUUID()).build());
        mockMvc.perform(post(BEER_V1_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(beerDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void handleDelete() throws Exception {
        mockMvc.perform(delete(BEER_V1_PATH + "/{beerId}", UUID.randomUUID()))
                .andExpect(status().isNoContent());
        verify(beerService).deleteById(Mockito.isA(UUID.class));
    }

    @Test
    void handleUpdate() throws Exception {
        var beerDto = BeerDto.builder()
                .beerName("Stella")
                .beerStyle("Ale")
                .upc(1L)
                .build();
        mockMvc.perform(put(BEER_V1_PATH + "/{beerId}", UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(beerDto)))
                .andExpect(status().isNoContent());
        verify(beerService).updateBeer(Mockito.isA(UUID.class), Mockito.isA(BeerDto.class));
    }
}