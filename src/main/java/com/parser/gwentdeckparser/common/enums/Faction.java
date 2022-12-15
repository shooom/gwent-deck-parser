package com.parser.gwentdeckparser.common.enums;

import com.parser.gwentdeckparser.common.GwentNameble;
import com.parser.gwentdeckparser.common.GwentNumerable;

public enum Faction implements GwentNumerable, GwentNameble {
    NEUTRAL(0, "neutral"),
    MONSTERS(1, "monsters"),
    NILFGAARD(2, "nilfgaard"),
    NORTHERN_REALMS(3, "northern reams"),
    SCOIATAEL(4, "scoiatael"),
    SKELLIGE(5, "skellige"),
    SYNDICATE(6, "syndicate");

    private int gwentNumber;
    private String gwentName;

    Faction(int num, String name) {
        this.gwentName = name;
        this.gwentNumber = num;
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
