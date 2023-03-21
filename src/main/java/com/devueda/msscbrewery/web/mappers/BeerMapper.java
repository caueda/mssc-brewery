package com.devueda.msscbrewery.web.mappers;

import com.devueda.msscbrewery.domain.Beer;
import com.devueda.msscbrewery.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {
    BeerDto beerToBeerDto(Beer beer);
    Beer beerDtoToBeer(BeerDto beerDto);
}
