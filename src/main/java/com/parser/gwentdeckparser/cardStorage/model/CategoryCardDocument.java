package com.parser.gwentdeckparser.cardStorage.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("gw_categories")
public class CategoryCardDocument extends BaseGwentEntity {

    public CategoryCardDocument(String gwentObjectId) {
        this.setGwentObjectId(gwentObjectId);
    }

    List<EmbeddedTranslation> translations;
}
