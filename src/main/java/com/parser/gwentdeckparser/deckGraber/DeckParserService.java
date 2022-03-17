package com.parser.gwentdeckparser.deckGraber;

import com.parser.gwentdeckparser.deckStructure.guide.Guide;
import com.parser.gwentdeckparser.deckStructure.guide.GuideList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeckParserService {
    private final GwentClient client;
    private final GwentDataParser parser;

    public String loadLeaders() throws IOException {
            return client.leaders();
    }

    public GuideList getTopDecks(long deckCount) throws IOException {
        return parser.parseGuideList(client.topDecks(deckCount));
    }

    public Guide getDeck(long deckId) throws IOException {
        return parser.parseGuide(client.deck(deckId, "en"));
    }
}
