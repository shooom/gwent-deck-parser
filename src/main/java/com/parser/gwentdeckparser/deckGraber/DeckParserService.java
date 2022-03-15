package com.parser.gwentdeckparser.deckGraber;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class DeckParserService {
    private final GwentClient client;

    public String loadLeaders() throws IOException {
            return client.leaders();
    }

    public String getTopDecks(long deckCount) throws IOException {
        return client.topDecks(deckCount);
    }

    public String getDeck(long deckId) throws IOException {
        return client.deck(deckId, "en");
    }
}
