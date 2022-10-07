package com.parser.gwentdeckparser.cardStorage.model.embedded;

public class EmbeddedTranslation {

    private String localizedName;

    public EmbeddedTranslation(String localizedName) {
        this.localizedName = localizedName;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }
}
