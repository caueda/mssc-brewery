package com.devueda.msscbrewery.services;

import com.devueda.msscbrewery.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
    BeerDto getBeerById(UUID beerId);

}
