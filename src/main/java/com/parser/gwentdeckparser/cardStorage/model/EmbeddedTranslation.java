package com.parser.gwentdeckparser.cardStorage.model;

public class EmbeddedTranslation {

    private String localizedName;

    public EmbeddedTranslation(String localizedName) {
        this.localizedName = localizedName;
    }

    public void setLocale(String localizedName) {
        this.localizedName = localizedName;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }
}
