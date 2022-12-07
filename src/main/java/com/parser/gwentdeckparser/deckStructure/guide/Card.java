package com.parser.gwentdeckparser.deckStructure.guide;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Card {
    private long id;
    private String name;
    private int power;
    private int armour;
    private String rarity;
    private Faction faction;
    private String cardGroup;
    private int craftingCost;
    private int provisionsCost;
    private Integer primaryCategoryId;
    private String type;
    private String categoryName;
    private String fluff;
    private List<Integer> categoryIds;
    private List<List<CardTooltip>> tooltip;
}
