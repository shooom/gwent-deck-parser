package com.parser.gwentdeckparser.cardStorage.service;

import com.parser.gwentdeckparser.deckStructure.deckBuilder.Card;
import java.util.List;
import java.util.Map;

public interface CardStorageService {

    void saveCard(Card card);

    Card getCardById(Long id);

    int saveCardList(List<Card> cards);

    List<Card> findCardsByFilter(Map<String, String> filters);
}
