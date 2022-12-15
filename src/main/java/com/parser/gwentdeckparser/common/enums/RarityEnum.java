package com.parser.gwentdeckparser.common.enums;

import com.parser.gwentdeckparser.common.GwentNameble;
import com.parser.gwentdeckparser.common.GwentNumerable;

public enum RarityEnum implements GwentNumerable, GwentNameble {
    LEGENDARY(25, "legendary"),
    EPIC(20, "epic"),
    RARE(15, "rare"),
    COMMON(5, "common");

    private int gwentNumber;
    private String gwentName;

    RarityEnum(int gwentNumber, String gwentName) {
        this.gwentNumber = gwentNumber;
        this.gwentName = gwentName;
    }

    @Override
    public int getGwentNumber() {
        return gwentNumber;
    }

    @Override
    public String getGwentName() {
        return gwentName;
    }
}
