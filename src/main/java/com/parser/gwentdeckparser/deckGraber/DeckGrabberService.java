package com.parser.gwentdeckparser.deckGraber;

import com.parser.gwentdeckparser.deckStructure.guide.Guide;
import com.parser.gwentdeckparser.deckStructure.guide.GuideList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeckGrabberService {
    private final GwentClient client;

    public GuideList getTopDecks(long deckCount) {
        return client.loadTopDecks(deckCount);
    }

    public Guide getDeckById(long deckId) {
        return client.loadDeckById(deckId, "en");
    }
}
