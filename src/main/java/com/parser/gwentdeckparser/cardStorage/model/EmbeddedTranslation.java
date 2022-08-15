package com.parser.gwentdeckparser.cardStorage.model;

public class EmbeddedTranslation {

    private String locale;
    private String localizedName;

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }
}
