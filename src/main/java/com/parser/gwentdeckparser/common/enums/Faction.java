package com.parser.gwentdeckparser.common.enums;

public enum Faction {
    NEUTRAL(0, "Neutral"),
    MONSTERS(1, "Monsters"),
    NILFGAARD(2, "Nilfgaard"),
    NORTHERN_REALMS(3, "Northern reams"),
    SCOIATAEL(4, "Scoia'tael"),
    SKELLIGE(5, "Skellige"),
    SYNDICATE(6, "Syndicate");

    private int num;
    private String name;

    Faction(int num, String name) {
        this.name = name;
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public String getName() {
        return name;
    }
}
