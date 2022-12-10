package com.parser.gwentdeckparser.common.enums;

import com.parser.gwentdeckparser.common.GwentNameble;
import com.parser.gwentdeckparser.common.GwentNumerable;
import lombok.Getter;

@Getter
public enum CardGroupEnum implements GwentNumerable, GwentNameble {
    LEADER(5, "leader"),
    GOLD(3, "gold"),
    BRONZE(1, "bronze");

    private int gwentNumber;
    private String gwentName;

    CardGroupEnum(int num, String name) {
        this.gwentNumber = num;
        this.gwentName = name;
    }
}
