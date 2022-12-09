package com.parser.gwentdeckparser.cardStorage.model;

import com.parser.gwentdeckparser.cardStorage.model.embedded.KeyWordTranslation;
import com.parser.gwentdeckparser.common.enums.LocalisationEnum;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document("gw_keywords")
public class KeyWordDocument extends BaseGwentEntity {

    Map<LocalisationEnum, KeyWordTranslation> translations;
    public KeyWordDocument(String gwentObjectId) {
        this.setGwentObjectId(gwentObjectId);
        translations = new HashMap<>();
    }

    public Map<LocalisationEnum, KeyWordTranslation> getTranslations() {
        return translations;
    }

    public void setTranslations(Map<LocalisationEnum, KeyWordTranslation> translations) {
        this.translations = translations;
    }
}
