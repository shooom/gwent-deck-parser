package com.parser.gwentdeckparser.cardStorage.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class BaseGwentEntity {
    @Id
    private String id;

    /**
     * Unique value (number, word) from Gwent site
     */
    @Field("gwent_id")
    private String gwentObjectId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGwentObjectId() {
        return gwentObjectId;
    }

    public void setGwentObjectId(String gwentObjectId) {
        this.gwentObjectId = gwentObjectId;
    }
}
