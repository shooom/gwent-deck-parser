package com.parser.gwentdeckparser.cardsLoader.service;

import com.parser.gwentdeckparser.cardStorage.model.CardDocument;
import com.parser.gwentdeckparser.cardStorage.model.embedded.CardTranslation;
import com.parser.gwentdeckparser.cardStorage.service.CardDocConverter;
import com.parser.gwentdeckparser.cardStorage.service.CardMongoStorageService;
import com.parser.gwentdeckparser.cardsLoader.dto.LoaderResultDto;
import com.parser.gwentdeckparser.common.LocalisationEnum;
import com.parser.gwentdeckparser.deckGraber.CardGrabberService;
import com.parser.gwentdeckparser.deckStructure.deckBuilder.Card;
import com.parser.gwentdeckparser.deckStructure.deckBuilder.Translation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CardsLoaderService {

    private final CardMongoStorageService storageService;
    private final KeyWordLoaderService keyWordLoader;
    private final CategoryLoaderService categoryLoader;
    private final CardGrabberService grabberService;

    @Autowired
    public CardsLoaderService(CardMongoStorageService storageService, KeyWordLoaderService keyWordLoader, CategoryLoaderService categoryLoader, CardGrabberService grabberService) {
        this.grabberService = grabberService;
        this.storageService = storageService;
        this.keyWordLoader = keyWordLoader;
        this.categoryLoader = categoryLoader;
    }

    public LoaderResultDto loadCardsToStorage(Map<String, String> filters) {
        LocalisationEnum locale = getLocale(filters);
        List<Card> cards = grabberService.getCards(filters);

        cards.forEach(card -> {
            CardDocument cardDoc = saveOrUpdateCard(card, locale);
            cardDoc.setCategoryNames(categoryLoader.saveOrUpdateCategories(card, locale));
            cardDoc.setKeywords(keyWordLoader.saveOrUpdateKeyword(card, locale));
            cardDoc.setTranslations(updateTranslations(cardDoc, card, locale));
            storageService.save(cardDoc);
        });
        return new LoaderResultDto();
    }

    private Map<LocalisationEnum, CardTranslation> updateTranslations(CardDocument cardDoc, Card card, LocalisationEnum locale) {
        Map<LocalisationEnum, CardTranslation>  docTranslations = cardDoc.getTranslations();
        Translation newTranslation = card.getTranslations().get(locale.getLocalisation());
        CardTranslation newCardTranslation = new CardTranslation(newTranslation.getName(), newTranslation.getTooltip(), newTranslation.getFluff());
        docTranslations.put(locale, newCardTranslation);

        return docTranslations;
    }

    private LocalisationEnum getLocale(Map<String, String> filters) {
        return LocalisationEnum.valueOf(filters.get("locale").toUpperCase());
    }

    private CardDocument saveOrUpdateCard(Card card, LocalisationEnum locale) {
        System.out.println("LOADER: HANDLING CARD " + card.getGwId() + "... ");
        Optional<CardDocument> optionalDoc = storageService.findByGwentId(card.getGwId());

        if (optionalDoc.isEmpty()) {
            CardDocument newCard = converter.convert(card, locale);
            System.out.println("LOADER: ADDED NEW CARD " + newCard.getGwentObjectId());
            return newCard;
        }

        CardDocument savedCard = optionalDoc.get();
        updateCardData(savedCard, card, locale);
        System.out.println("LOADER: UPDATED CARD " + savedCard.getGwentObjectId());
        return savedCard;
    }

    /**
     * Update most updatable parts of cards (power, armour, translations)
     *
     * @param storedDocument
     * @param newCard
     * @param locale
     */
    private void updateCardData(CardDocument storedDocument, Card newCard, LocalisationEnum locale) {
        storedDocument.setPower(newCard.getPower());
        storedDocument.setArmour(newCard.getArmour());
        storedDocument.setProvisionCost(newCard.getProvisionCost());
    }

    private CardDocConverter converter = (Card card, LocalisationEnum locale) -> {
        CardDocument doc = new CardDocument();
        doc.setGwentObjectId(card.getGwId());
        doc.setCraftingCost(card.getCraftingCost());
        doc.setProvisionCost(card.getProvisionCost());
        doc.setAvailability(card.getAvailability());
        doc.setType(card.getType());
        doc.setPrimaryCategoryId(card.getPrimaryCategoryId());
        doc.setSecondaryFactions(card.getSecondaryFactions());
        doc.setArmour(card.getArmour());
        doc.setCardGroup(card.getCardGroup());
        doc.setFaction(card.getFaction());
        doc.setName(card.getName());
        doc.setPower(card.getPower());
        doc.setRarity(card.getRarity());
        return doc;
    };
}
