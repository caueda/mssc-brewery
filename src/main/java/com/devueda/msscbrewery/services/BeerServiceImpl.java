package com.devueda.msscbrewery.services;

import com.devueda.msscbrewery.web.model.BeerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public BeerDto getBeerById(UUID beerId) {
        return BeerDto.builder()
                .beerName("Galaxy Cat")
                .beerStyle("Pale Ale")
                .build();
    }
}
