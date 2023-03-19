package com.devueda.msscbrewery.web.controller;

import com.devueda.msscbrewery.services.BeerService;
import com.devueda.msscbrewery.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
public class BeerController {

    private final BeerService beerService;
    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeer(@PathVariable UUID beerId) {
        return ResponseEntity.ok(beerService.getBeerById(beerId));
    }

    @PostMapping
    public ResponseEntity handlePost(@RequestBody BeerDto beerDto) {
        var savedBeer = beerService.saveNewBeer(beerDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{beerId}")
                .buildAndExpand(savedBeer.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{beerId}")
    public ResponseEntity handleDelete(@PathVariable UUID beerId) {
        beerService.deleteById(beerId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleUpdate(@PathVariable UUID beerId, BeerDto beerDto) {
        beerService.updateBeer(beerId,  beerDto);
    }
}
