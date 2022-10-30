package com.parser.gwentdeckparser.cardStorage.model.embedded;

public class CardTranslation extends EmbeddedTranslation {

    private String tooltip;
    private String fluff;

    public CardTranslation(String localizedName, String tooltip, String fluff) {
        super(localizedName);
        this.fluff = fluff;
        this.tooltip = tooltip;
    }

    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public String getFluff() {
        return fluff;
    }

    public void setFluff(String fluff) {
        this.fluff = fluff;
    }
}

