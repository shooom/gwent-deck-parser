package com.parser.gwentdeckparser.common.enums;

import com.parser.gwentdeckparser.common.GwentNameble;
import com.parser.gwentdeckparser.common.GwentNumerable;

public enum Type implements GwentNumerable, GwentNameble {
    LEADER(1, "leader"),
    SPECIAL(2, "special"),
    UNIT(4, "unit"),
    ARTIFACT(8, "artifact"),
    TACTIC(16, "tactic");

    private int gwentNumber;
    private String gwentName;

    Type(int num, String name) {
        this.gwentNumber = num;
        this.gwentName = name;
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
