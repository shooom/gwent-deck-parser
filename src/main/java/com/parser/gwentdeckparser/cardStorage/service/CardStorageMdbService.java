package com.parser.gwentdeckparser.cardStorage.service;

import com.parser.gwentdeckparser.cardStorage.model.CardDocument;
import com.parser.gwentdeckparser.cardStorage.repository.CardRepository;
import com.parser.gwentdeckparser.common.LocalisationEnum;
import com.parser.gwentdeckparser.deckStructure.deckBuilder.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardStorageMdbService {

    private final CardRepository repository;

    public CardDocument save(Card card, LocalisationEnum locale) {
        String gwentId = card.getGwId();
        Optional<CardDocument> optEntity = repository.findByGwentObjectId(gwentId);

        if (optEntity.isEmpty()) {
         return repository.save(converter.convert(card));
        }
        CardDocument doc = optEntity.get();
        doc.getTranslations().put(locale.getLocalisation(), card.getTranslations().get(locale));

        return repository.save(doc);
    }

    public List<CardDocument> saveAll(List<Card> cards) {
        return repository.saveAll(listMapper(cards));
    }

    public CardDocument getByName(String name) {
        return repository.findCardByName(name);
    }

    private List<CardDocument> listMapper(List<Card> cards) {
        return cards.stream()
                .map(c -> converter.convert(c))
                .collect(Collectors.toList());
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
