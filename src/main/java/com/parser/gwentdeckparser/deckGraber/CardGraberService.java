package com.parser.gwentdeckparser.deckGraber;

import com.parser.gwentdeckparser.deckStructure.deckBuilder.Card;
import com.parser.gwentdeckparser.deckStructure.deckBuilder.CardContainer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CardGraberService {
    private final GwentClient client;

    public List<Card> getCards(Map<String, String> filters) {
        return client.cardSearch(filters).stream()
                .map(CardContainer::getSource)
                .collect(Collectors.toList());
    }

    public Card getCardById(String id) {
        return client.cardById(id).get(0).getSource();
    }
}