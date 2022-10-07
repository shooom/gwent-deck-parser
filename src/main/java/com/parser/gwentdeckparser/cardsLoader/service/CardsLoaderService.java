package com.parser.gwentdeckparser.cardsLoader.service;

import com.parser.gwentdeckparser.cardStorage.model.CardDocument;
import com.parser.gwentdeckparser.cardStorage.model.CategoryDocument;
import com.parser.gwentdeckparser.cardStorage.model.embedded.EmbeddedTranslation;
import com.parser.gwentdeckparser.cardStorage.service.CardDocConverter;
import com.parser.gwentdeckparser.cardStorage.service.CardMongoStorageService;
import com.parser.gwentdeckparser.cardStorage.service.CategoryMongoStorageService;
import com.parser.gwentdeckparser.cardsLoader.dto.LoaderResultDto;
import com.parser.gwentdeckparser.common.LocalisationEnum;
import com.parser.gwentdeckparser.deckGraber.CardGrabberService;
import com.parser.gwentdeckparser.deckStructure.deckBuilder.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CardsLoaderService {

    private final CardMongoStorageService storageService;
    private final CategoryMongoStorageService categoryService;
    private final KeyWordLoaderService keyWordLoader;
    private final CardGrabberService grabberService;

    @Autowired
    public CardsLoaderService(CardMongoStorageService storageService, CategoryMongoStorageService categoryService, KeyWordLoaderService keyWordLoader, CardGrabberService grabberService) {
        this.grabberService = grabberService;
        this.storageService = storageService;
        this.keyWordLoader = keyWordLoader;
        this.categoryService = categoryService;
    }

    public LoaderResultDto loadCardsToStorage(Map<String, String> filters) {
        //TODO: to think about correct locale searching
        LocalisationEnum locale = LocalisationEnum.valueOf(filters.get("locale").toUpperCase());
        List<Card> cards = grabberService.getCards(filters);

        cards.forEach(card -> {
            saveOrUpdateCategories(card, locale);
            keyWordLoader.saveOrUpdateKeyword(card, locale);
            saveOrUpdateCard(card, locale);
        });
        return new LoaderResultDto();
    }

    private void saveOrUpdateCard(Card card, LocalisationEnum locale) {
        System.out.println("LOADER: HANDLING CARD " + card.getGwId() + "... ");
        Optional<CardDocument> cardDoc = storageService.findByGwentId(card.getGwId());
        CardDocument newCard = converter.convert(card);

        if (cardDoc.isEmpty()) {
            System.out.println("LOADER: ADDED NEW CARD " + newCard.getGwentObjectId());
            storageService.save(newCard);
            return;
        }
        System.out.println("LOADER: UPDATED CARD " + newCard.getGwentObjectId());
        CardDocument savedCard = cardDoc.get();
        updateCardData(savedCard, newCard, locale);
        storageService.save(savedCard);
    }

    private void saveOrUpdateCategories(Card card, LocalisationEnum locale) {
        for (int i = 0; i < card.getCategoryNames().size(); i++) {
            String gwentId = card.getCategoryNames().get(i);
            String translation = card.getTranslations().get(locale.getLocalisation()).getCategories()[i];

            CategoryDocument category = resolveCategory(gwentId);
            category.setTranslations(locale, new EmbeddedTranslation(translation));
            categoryService.saveCategory(category);
        }
    }

    private CategoryDocument resolveCategory(String gwentId) {
        return categoryService.findByGwentId(gwentId).orElse(new CategoryDocument(gwentId));
    }

    /**
     * Update most updatable parts of cards (power, armour, translations)
     *
     * @param storedDocument
     * @param newCard
     * @param locale
     */
    private void updateCardData(CardDocument storedDocument, CardDocument newCard, LocalisationEnum locale) {
        //TODO: sometimes cards can change own type - need to add this update
        //TODO: check hashcode before update?
        storedDocument.setPower(newCard.getPower());
        storedDocument.setArmour(newCard.getArmour());
        storedDocument.setProvisionCost(newCard.getProvisionCost());
        storedDocument.setKeywords(newCard.getKeywords());

        if (storedDocument.getTranslations().get(locale.getLocalisation()) == null) {
            storedDocument.getTranslations().put(locale.getLocalisation(), newCard.getTranslations().get(locale.getLocalisation()));
        }
    }

    private CardDocConverter converter = (Card card) -> {
        CardDocument doc = new CardDocument();
        doc.setGwentObjectId(card.getGwId());
        doc.setCraftingCost(card.getCraftingCost());
        doc.setProvisionCost(card.getProvisionCost());
        doc.setAvailability(card.getAvailability());
        doc.setKeywords(card.getKeywords());
        doc.setType(card.getType());
        doc.setCategoryNames(card.getCategoryNames());
        doc.setPrimaryCategoryId(card.getPrimaryCategoryId());
        doc.setSecondaryFactions(card.getSecondaryFactions());
        doc.setArmour(card.getArmour());
        doc.setTranslations(card.getTranslations());
        doc.setCardGroup(card.getCardGroup());
        doc.setFaction(card.getFaction());
        doc.setName(card.getName());
        doc.setPower(card.getPower());
        doc.setRarity(card.getRarity());
        return doc;
    };
}
