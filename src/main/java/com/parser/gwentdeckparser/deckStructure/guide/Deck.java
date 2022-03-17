package com.parser.gwentdeckparser.deckStructure.guide;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Deck {
    private long id;
    private String hash;
    private String dataVersionCrc;
    private String modified;
    private int craftingCost;
    private int provisionCost;
    private int cardsCount;
    private int unitsCount;
    private Faction faction;
    private Card leader;
    private Card stratagem;
    private Card[] cards;
}
