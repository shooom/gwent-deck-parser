package com.parser.gwentdeckparser.cardStorage.model.embedded;

public class KeyWordTranslation extends EmbeddedTranslation {
    private String description;

    public KeyWordTranslation(String localizedName) {
        super(localizedName);
        setLocalizedName(localizedName);
        description = "";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
