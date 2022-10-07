package com.parser.gwentdeckparser.cardStorage.model;

import com.parser.gwentdeckparser.cardStorage.model.embedded.EmbeddedTranslation;
import com.parser.gwentdeckparser.common.LocalisationEnum;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document("gw_categories")
public class CategoryDocument extends BaseGwentEntity {

    private Map<LocalisationEnum, EmbeddedTranslation> translations;
    public CategoryDocument(String gwentObjectId) {
        this.setGwentObjectId(gwentObjectId);
        this.translations = new HashMap<>();
    }

    public Map<LocalisationEnum, EmbeddedTranslation> getTranslations() {
        return translations;
    }

    public void setTranslations(LocalisationEnum locale, EmbeddedTranslation translation) {
        this.translations.put(locale, translation);
    }
}
