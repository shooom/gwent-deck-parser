package com.parser.gwentdeckparser.deckGraber;

import com.parser.gwentdeckparser.deckStructure.deckBuilder.Card;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CardParserService {
    private final GwentClient client;
    private final GwentDataParser parser;

    public List<Card> getCards(Map<String, String> filters) throws IOException {
        return parser.parseCardList(client.cardSearch(filters));
    }

    public Card getCardById(String id) throws IOException {
        return parser.parseCardList(client.cardById(id)).get(0);
    }
}
