package com.parser.gwentdeckparser.api;

import com.parser.gwentdeckparser.deckGraber.DeckGrabberService;
import com.parser.gwentdeckparser.deckStructure.guide.Guide;
import com.parser.gwentdeckparser.deckStructure.guide.GuideList;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/decks")
@RequiredArgsConstructor
public class DecksParserController {
    private final DeckGrabberService deckGrabber;

    @GetMapping("/top")
    public GuideList getTopDecks(@RequestParam(value = "deckNum", defaultValue = "3") long deckNum) {
        return deckGrabber.getTopDecks(deckNum);
    }

    @GetMapping("/{deckId}")
    public Guide getDeck(@PathVariable(value = "deckId") long deckId) {
        return deckGrabber.getDeckById(deckId);
    }
}
