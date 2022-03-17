package com.parser.gwentdeckparser.api;

import com.parser.gwentdeckparser.deckGraber.FiltersService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@AllArgsConstructor
@RequestMapping("/filters")
public class FilterController {
    private final FiltersService service;

    @GetMapping
    public ResponseEntity<Map<String, Map>> filters() {
        return ok(service.getFilters());
    }
}
