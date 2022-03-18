package com.parser.gwentdeckparser.api;

import com.parser.gwentdeckparser.deckGraber.CardGrabberService;
import com.parser.gwentdeckparser.deckGraber.DeckGraberService;
import com.parser.gwentdeckparser.deckStructure.deckBuilder.Card;
import com.parser.gwentdeckparser.deckStructure.guide.Guide;
import com.parser.gwentdeckparser.deckStructure.guide.GuideList;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("deck")
@RequiredArgsConstructor
public class ParserController {
    private final DeckGraberService deckGrabber;
    private final CardGrabberService cardGrabber;

    @GetMapping("/top")
    public GuideList getTopDecks(@RequestParam(value = "deckNum", defaultValue = "3") long deckNum) {
        return deckGrabber.getTopDecks(deckNum);
    }

    @GetMapping("/{deckId}")
    public Guide getDeck(@PathVariable(value = "deckId") long deckId) {
        return deckGrabber.getDeckById(deckId);
    }

    @GetMapping("/cards")
    public List<Card> getCards(@RequestParam Map<String, String> filters) {
        return cardGrabber.getCards(filters);
    }

    @GetMapping("/cards/{cardId}")
    public Card getCardById(@PathVariable String cardId) {
        return cardGrabber.getCardById(cardId);
    }
}
