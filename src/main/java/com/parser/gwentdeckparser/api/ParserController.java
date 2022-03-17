package com.parser.gwentdeckparser.api;

import com.parser.gwentdeckparser.deckGraber.DeckParserService;
import com.parser.gwentdeckparser.deckStructure.Guide;
import com.parser.gwentdeckparser.deckStructure.GuideList;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.Map;

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
    public GuideList getTopDecks(@RequestParam(value = "deckNum", defaultValue = "3") long deckNum) throws IOException {
        return service.getTopDecks(deckNum);
    }

    @GetMapping("/{deckId}")
    public Guide getDeck(@PathVariable(value = "deckId") long deckId) throws IOException {
        return service.getDeck(deckId);
    }

    @GetMapping("/cards")
    public String getCards(@RequestParam Map<String, String> filters) throws IOException {
        return service.getCards(filters);
    }

    @GetMapping("/cards/{cardId}")
    public String getCardById(@PathVariable String cardId) throws IOException {
        return service.getCardById(cardId);
    }
}
