package com.parser.gwentdeckparser.api;

import com.parser.gwentdeckparser.cardStorage.model.CardDocument;
import com.parser.gwentdeckparser.cardStorage.service.CardStorageMdbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/storage")
public class CardsStorageController {

    private final CardStorageMdbService storageService;

    @GetMapping
    public ResponseEntity<CardDocument> getByName(@RequestParam(name = "name") String name) {
        return ResponseEntity.ok(storageService.getByName(name));
    }
}