package com.parser.gwentdeckparser.cardStorage.model;

import com.parser.gwentdeckparser.deckStructure.deckBuilder.Translation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("gw_cards")
public class CardDocument {
    @Id
    private String id;

    private long gwId;
    private int craftingCost;
    private int provisionCost;
    private int availability;
    private List<String> keywords;
    private int[] categoryNames;
    private int type;
    private Integer primaryCategoryId;
    private int[] secondaryFactions;
    private int armour;
    private Map<String, Translation> translations;
    private int cardGroup;
    private int faction;
    private String name;
    private int power;
    private int rarity;
}
