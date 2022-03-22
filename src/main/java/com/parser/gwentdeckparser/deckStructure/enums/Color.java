package com.parser.gwentdeckparser.deckStructure.enums;

import lombok.Getter;

@Getter
public enum Color {
    GOLD(3, "Gold"),
    BRONZE(1, "Bronze");

    private int num;
    private String name;

    Color(int num, String name) {
        this.num = num;
        this.name = name;
    }
}
