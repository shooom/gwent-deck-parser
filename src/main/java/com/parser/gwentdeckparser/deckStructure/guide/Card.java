package com.parser.gwentdeckparser.deckStructure.guide;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private Integer primaryCategoryId;
    private List<Integer> categoryIds;
    private List<List<CardTooltip>> tooltip;
}
