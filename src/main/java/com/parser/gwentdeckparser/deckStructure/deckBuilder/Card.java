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
    private String name;
    private int power;
    private int armour;
    private int rarity;
    private int faction;
    @Json(name = "card_group")
    private int cardGroup;
    @Json(name = "crafting_cost")
    private int craftingCost;
    @Json(name = "provisions_cost")
    private int provisionCost;
    @Json(name = "primary_category_id")
    private Integer primaryCategoryId;
    private int type;

    // deck builder fields part
    private int availability;
    private List<String> keywords;
    @Json(name = "category_names")
    private List<String> categoryNames;
    @Json(name = "secondary_factions")
    private int[] secondaryFactions;
    private Map<String, Translation> translations;

    // images part
    @Json(name = "preview_img_path")
    private CardImagesBlock previewImages;
    @Json(name = "slot_img_path")
    private CardImagesBlock slotImages;
}
