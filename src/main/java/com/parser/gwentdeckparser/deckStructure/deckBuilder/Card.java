package com.parser.gwentdeckparser.deckStructure.deckBuilder;

import com.squareup.moshi.Json;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class Card {
    @Json(name = "id")
    private String gwId;
    @Json(name = "crafting_cost")
    private int craftingCost;
    @Json(name = "provisions_cost")
    private int provisionCost;
    private int availability;
    private List<String> keywords;
    @Json(name = "category_names")
    private List<String> categoryNames;
    private int type;
    @Json(name = "primary_category_id")
    private Integer primaryCategoryId;
    @Json(name = "secondary_factions")
    private int[] secondaryFactions;
    private int armour;
    private Map<String, Translation> translations;
    @Json(name = "card_group")
    private int cardGroup;
    private int faction;
    private String name;
    private int power;
    private int rarity;
}
