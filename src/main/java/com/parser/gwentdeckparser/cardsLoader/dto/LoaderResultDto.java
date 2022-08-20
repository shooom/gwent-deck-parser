package com.parser.gwentdeckparser.cardsLoader.dto;

public class LoaderResultDto {
    private Long cardsAdded = 0L;
    private Long cardsUpdated = 0L;

    public Long getCardsAdded() {
        return cardsAdded;
    }

    public void setCardsAdded(Long cardsAdded) {
        this.cardsAdded = cardsAdded;
    }

    public Long getCardsUpdated() {
        return cardsUpdated;
    }

    public void setCardsUpdated(Long cardsUpdated) {
        this.cardsUpdated = cardsUpdated;
    }
}
