package com.parser.gwentdeckparser.deckGraber;

import com.parser.gwentdeckparser.deckStructure.Guide;
import com.parser.gwentdeckparser.deckStructure.GuideList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeckParserService {
    private final GwentClient client;
    private final DeckParser parser;

    public String loadLeaders() throws IOException {
            return client.leaders();
    }

    public GuideList getTopDecks(long deckCount) throws IOException {
        return parser.parseGuideList(client.topDecks(deckCount));
    }

    public Guide getDeck(long deckId) throws IOException {
        return parser.parseGuide(client.deck(deckId, "en"));
    }

    public String getCards(Map<String, String> filters) throws IOException {
        return client.cardSearch(filters);
    }

    public String getCardById(String id) throws IOException {
        return client.cardById(id);
    }
}
