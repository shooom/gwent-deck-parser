package com.parser.gwentdeckparser.cardsLoader.controller;

import com.parser.gwentdeckparser.cardsLoader.service.CardsLoaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/loader/cards")
public class CardsLoaderController {

    private final CardsLoaderService loaderService;

    @Autowired
    public CardsLoaderController(CardsLoaderService loaderService) {
        this.loaderService = loaderService;
    }

    @GetMapping
    public ResponseEntity<?> loadCards(@RequestParam Map<String, String> filters) {
        return ResponseEntity.ok().body(loaderService.loadCardsToStorage(filters));
    }
}
