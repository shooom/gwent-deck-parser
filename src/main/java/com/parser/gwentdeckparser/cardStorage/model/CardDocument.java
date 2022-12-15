package com.parser.gwentdeckparser.cardStorage.model;

import com.parser.gwentdeckparser.cardStorage.model.embedded.CardTranslation;
import com.parser.gwentdeckparser.common.enums.LocalisationEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@Document("gw_cards")
public class CardDocument extends BaseGwentEntity {
    private int craftingCost;
    private int provisionCost;
    private int availability;
    @DBRef(lazy = true)
    private List<KeyWordDocument> keywords;
    @DBRef(lazy = true)
    private List<CategoryDocument> categoryNames;
    private int type;
    private Integer primaryCategoryId;
    private int[] secondaryFactions;
    private int armour;
    private Map<LocalisationEnum, CardTranslation> translations;
    private int cardGroup;
    private int faction;
    private String name;
    private int power;
    private int rarity;

    public CardDocument() {
        this.translations = new HashMap<>();
    }
}
