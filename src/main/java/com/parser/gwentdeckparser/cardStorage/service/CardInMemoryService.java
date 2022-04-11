package com.parser.gwentdeckparser.cardStorage.service;

import com.parser.gwentdeckparser.deckStructure.deckBuilder.Card;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class CardInMemoryService implements CardStorageService {

    private CopyOnWriteArrayList<Card> cardStorage = new CopyOnWriteArrayList();

    @Override
    public void saveCard(Card card) {
        cardStorage.addIfAbsent(card);
    }

    @Override
    public Card getCardById(Long id) {
        return null;
    }

    @Override
    public int saveCardList(List<Card> cards) {
        return 0;
    }

    @Override
    public List<Card> findCardsByFilter(Map<String, String> filters) {
        return null;
    }
}
