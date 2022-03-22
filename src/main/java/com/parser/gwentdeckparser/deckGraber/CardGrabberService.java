package com.parser.gwentdeckparser.deckGraber;

import com.parser.gwentdeckparser.deckStructure.deckBuilder.Card;
import com.parser.gwentdeckparser.deckStructure.deckBuilder.CardContainer;
import com.parser.gwentdeckparser.exceptions.CardNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CardGrabberService {
    private final GwentClient client;

    public List<Card> getCards(Map<String, String> filters) {
        return client.searchCards(filters).stream()
                .map(CardContainer::getSource)
                .collect(Collectors.toList());
    }

    public List<Card> getLeaders(Map<String, String> filters) {
        return client.loadLeaders(filters).stream()
                .map(CardContainer::getSource)
                .collect(Collectors.toList());
    }

    public Card getCardById(String id) {
        var result = client.loadCardById(id);
        if (result.isEmpty()) {
            throw new CardNotFoundException("Card with id=" + id + " not found");
        }
        return client.loadCardById(id).get(0).getSource();
    }
}
