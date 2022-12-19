package com.parser.gwentdeckparser.cardsLoader.service;

import com.parser.gwentdeckparser.cardStorage.model.CardDocument;
import com.parser.gwentdeckparser.cardStorage.model.embedded.CardTranslation;
import com.parser.gwentdeckparser.cardStorage.service.CardDocConverter;
import com.parser.gwentdeckparser.cardStorage.service.CardMongoStorageService;
import com.parser.gwentdeckparser.cardsLoader.dto.LoaderResultDto;
import com.parser.gwentdeckparser.common.enums.LocalisationEnum;
import com.parser.gwentdeckparser.deckGraber.CardGrabberService;
import com.parser.gwentdeckparser.deckStructure.deckBuilder.Card;
import com.parser.gwentdeckparser.deckStructure.deckBuilder.CardContainer;
import com.parser.gwentdeckparser.deckStructure.deckBuilder.Translation;
import com.parser.gwentdeckparser.exceptions.GwentParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static com.parser.gwentdeckparser.common.enums.LocalisationEnum.*;

@Service
public class CardsLoaderService {

    private static final String LOCALISATION_FILTER_MUST_NOT_BE_EMPTY =
            "Localisation filter need to have next values: [%s]";
    private static final String[] SUPPORTED_LOCALISATIONS = {
            RU.getLocalisation(),
            EN.getLocalisation(),
            DE.getLocalisation()
    };
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
            CardDocument cardDoc = prepareNextCard(card, locale);

            if (!card.getConnectedCards().isEmpty()) {
                List<CardDocument> connectedCards = card.getConnectedCards().stream()
                        .map(CardContainer::getSource)
                        .map(connectedCard -> storageService.save(prepareNextCard(connectedCard, locale)))
                        .toList();
                cardDoc.setConnectedCards(connectedCards);
            }
            storageService.save(cardDoc);
        });
        return new LoaderResultDto();
    }

    private CardDocument prepareNextCard(Card nextCard, LocalisationEnum locale) {
        CardDocument cardDoc = saveOrUpdateCard(nextCard, locale);
        cardDoc.setCategoryNames(categoryLoader.saveOrUpdateCategories(nextCard, locale));
        cardDoc.setKeywords(keyWordLoader.saveOrUpdateKeyword(nextCard, locale));
        cardDoc.setTranslations(updateTranslations(cardDoc, nextCard, locale));

        return cardDoc;
    }

    private Map<LocalisationEnum, CardTranslation> updateTranslations(CardDocument cardDoc, Card card, LocalisationEnum locale) {
        Map<LocalisationEnum, CardTranslation> docTranslations = cardDoc.getTranslations();
        Translation newTranslation = card.getTranslations().get(locale.getLocalisation());
        CardTranslation newCardTranslation = new CardTranslation(newTranslation.getName(), newTranslation.getTooltip(), newTranslation.getFluff());
        docTranslations.put(locale, newCardTranslation);

        return docTranslations;
    }

    private CardDocument saveOrUpdateCard(Card card, LocalisationEnum locale) {
        System.out.println("LOADER: CARD PROCESSING " + card.getGwId() + "... ");
        Optional<CardDocument> optionalDoc = storageService.findByGwentId(card.getGwId());

        if (optionalDoc.isEmpty()) {
            System.out.println("LOADER: ADDED NEW CARD");
            CardDocument newCard = converter.convert(card, locale);
            return newCard;
        }

        System.out.println("LOADER: UPDATED CARD ");
        CardDocument savedCard = optionalDoc.get();
        updateCardData(savedCard, card, locale);
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

    private LocalisationEnum getLocale(Map<String, String> filters) {
        String locale = filters.get("locale");
        if (Objects.isNull(locale)) {
            throw new GwentParserException(String.format(
                    LOCALISATION_FILTER_MUST_NOT_BE_EMPTY, String.join(", ", SUPPORTED_LOCALISATIONS)));
        }
        return LocalisationEnum.valueOf(filters.get("locale").toUpperCase());
    }
}
