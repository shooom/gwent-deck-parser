package com.parser.gwentdeckparser.api;

import com.parser.gwentdeckparser.deckGraber.DeckParserService;
import com.parser.gwentdeckparser.deckStructure.Guide;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("deck")
@RequiredArgsConstructor
public class ParserController {
    private final DeckParserService service;

    @GetMapping("/leaders")
    public String getLeaders() throws IOException {
        return service.loadLeaders();
    }

    @GetMapping("/top")
    public String getTopDecks(@RequestParam(value = "deckNum", defaultValue = "3") long deckNum) throws IOException {
        return service.getTopDecks(deckNum);
    }

    @GetMapping("/{deckId}")
    public Guide getDeck(@PathVariable(value = "deckId") long deckId) throws IOException {
        return service.getDeck(deckId);
    }
}
