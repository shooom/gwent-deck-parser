package com.parser.gwentdeckparser.deckStructure;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Guide {
    private long id;
    private String name;
    private String author;
    private String language;
    private int votes;
    private Faction faction;
    private int craftingCost;
    private boolean invalid;
    private Deck deck;
}
