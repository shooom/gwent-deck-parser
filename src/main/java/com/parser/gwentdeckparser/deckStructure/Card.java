package com.parser.gwentdeckparser.deckStructure;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Card {
    private long id;
    private String name;
    private String fluff;
    private String rarity;
    private Faction faction;
    private String cardGroup;
    private String categoryName;
    private String type;
    private int craftingCost;
    private int power;
    private int provisionsCost;
    private int armour;
    private int primaryCategoryId;
    private int[] categoryIds;
    private CardTooltip[] tooltip;
}
