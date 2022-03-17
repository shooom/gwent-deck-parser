package com.parser.gwentdeckparser.api;

import com.parser.gwentdeckparser.deckGraber.CardParserService;
import com.parser.gwentdeckparser.deckGraber.DeckParserService;
import com.parser.gwentdeckparser.deckStructure.deckBuilder.Card;
import com.parser.gwentdeckparser.deckStructure.guide.Guide;
import com.parser.gwentdeckparser.deckStructure.guide.GuideList;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("deck")
@RequiredArgsConstructor
public class ParserController {
    private final DeckParserService deckParserService;
    private final CardParserService cardParserService;

    @GetMapping("/leaders")
    public String getLeaders() throws IOException {
        return deckParserService.loadLeaders();
    }

    @GetMapping("/top")
    public GuideList getTopDecks(@RequestParam(value = "deckNum", defaultValue = "3") long deckNum) throws IOException {
        return deckParserService.getTopDecks(deckNum);
    }

    @GetMapping("/{deckId}")
    public Guide getDeck(@PathVariable(value = "deckId") long deckId) throws IOException {
        return deckParserService.getDeck(deckId);
    }

    @GetMapping("/cards")
    public List<Card> getCards(@RequestParam Map<String, String> filters) throws IOException {
        return cardParserService.getCards(filters);
    }

    @GetMapping("/cards/{cardId}")
    public Card getCardById(@PathVariable String cardId) throws IOException {
        return cardParserService.getCardById(cardId);
    }
}
