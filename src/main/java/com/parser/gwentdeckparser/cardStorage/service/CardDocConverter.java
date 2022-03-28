package com.parser.gwentdeckparser.cardStorage.service;

import com.parser.gwentdeckparser.cardStorage.model.CardDocument;
import com.parser.gwentdeckparser.deckStructure.deckBuilder.Card;

@FunctionalInterface
public interface CardDocConverter {

    CardDocument convert(Card card);
}
