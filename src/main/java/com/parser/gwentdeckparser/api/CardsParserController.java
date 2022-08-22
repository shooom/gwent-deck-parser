package com.parser.gwentdeckparser.api;

import com.parser.gwentdeckparser.deckGraber.CardGrabberService;
import com.parser.gwentdeckparser.deckStructure.deckBuilder.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cards")
public class CardsParserController {
    private final CardGrabberService cardGrabber;

    @Autowired
    CardsParserController(CardGrabberService cardGrabber) {
        this.cardGrabber = cardGrabber;
    }

    @GetMapping
    public List<Card> getCards(@RequestParam Map<String, String> filters) {
        return cardGrabber.getCards(filters);
    }

    @GetMapping("/{cardId}")
    public Card getCardById(@PathVariable String cardId, String locale) {
        Card card = cardGrabber.getCardById(cardId, locale);

        return cardGrabber.getCardById(cardId, locale);
    }

    @GetMapping("/leaders")
    public List<Card> getLeaders(@RequestParam Map<String, String> filters) {
        return cardGrabber.getLeaders(filters);
    }
}
