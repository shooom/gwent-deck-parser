package com.parser.gwentdeckparser.api;

import com.parser.gwentdeckparser.cardStorage.model.CardDocument;
import com.parser.gwentdeckparser.cardStorage.service.CardMongoStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/storage/cards")
public class CardsStorageController {

    private final CardMongoStorageService storageService;

    @GetMapping
    public ResponseEntity<CardDocument> getByName(@RequestParam(name = "name") String name) {
        return ResponseEntity.ok(storageService.getByName(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardDocument> getByGwentId(@PathVariable("id") String gwentId) {
        return ResponseEntity.ok(storageService.getByGwentId(gwentId));
    }
}