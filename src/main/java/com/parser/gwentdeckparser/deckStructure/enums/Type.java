package com.parser.gwentdeckparser.deckStructure.enums;

public enum Type {
    LEADER(1, "Leader"),
    UNIT(4, "Unit"),
    SPECIAL(2, "Special"),
    ARTIFACT(8, "Artifact"),
    TACTIC(16, "Tactic");

    private int num;
    private String name;

    Type(int num, String name) {
        this.num = num;
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public String getName() {
        return name;
    }
}
