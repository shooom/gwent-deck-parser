package com.parser.gwentdeckparser.cardStorage.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("gw_cards")
public class KeyWordDocument extends BaseGwentEntity {

    public KeyWordDocument(String gwentObjectId) {
        this.setGwentObjectId(gwentObjectId);
    }
    List<KeyWordTranslation> translations;
}
