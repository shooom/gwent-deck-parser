package com.parser.gwentdeckparser.api;

import com.parser.gwentdeckparser.cardStorage.service.CardStorageMdbService;
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
    private final CardStorageMdbService storageService;

    @Autowired
    CardsParserController(CardGrabberService cardGrabber, CardStorageMdbService storageService) {
        this.cardGrabber = cardGrabber;
        this.storageService = storageService;
    }

    @GetMapping
    public List<Card> getCards(@RequestParam Map<String, String> filters) {
        return cardGrabber.getCards(filters);
    }

    @GetMapping("/{cardId}")
    public Card getCardById(@PathVariable String cardId) {
        Card card = cardGrabber.getCardById(cardId);
        storageService.save(card);

        return cardGrabber.getCardById(cardId);
    }

    @GetMapping("/leaders")
    public List<Card> getLeaders(@RequestParam Map<String, String> filters) {
        return cardGrabber.getLeaders(filters);
    }
}
