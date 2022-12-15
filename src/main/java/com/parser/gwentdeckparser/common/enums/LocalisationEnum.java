package com.parser.gwentdeckparser.common.enums;

public enum LocalisationEnum {

    EN("en"),
    RU("ru"),
    DE("de");

    private String localisation;

    LocalisationEnum(String localisation) {
        this.localisation = localisation;
    }

    public String getLocalisation() {
        return localisation;
    }
}
