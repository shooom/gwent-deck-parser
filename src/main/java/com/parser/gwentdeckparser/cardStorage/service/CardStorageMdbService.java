package com.parser.gwentdeckparser.cardStorage.service;

import com.parser.gwentdeckparser.cardStorage.model.CardDocument;
import com.parser.gwentdeckparser.cardStorage.repository.CardRepository;
import com.parser.gwentdeckparser.deckStructure.deckBuilder.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardStorageMdbService {

    private final CardRepository repository;

    private CardDocConverter converter = (Card c) -> {
        CardDocument doc = new CardDocument();
        doc.setGwId(c.getGwId());
        doc.setCraftingCost(c.getCraftingCost());
        doc.setProvisionCost(c.getProvisionCost());
        doc.setAvailability(c.getAvailability());
        doc.setKeywords(c.getKeywords());
        doc.setType(c.getType());
        doc.setCategoryNames(c.getCategoryNames());
        doc.setPrimaryCategoryId(c.getPrimaryCategoryId());
        doc.setSecondaryFactions(c.getSecondaryFactions());
        doc.setArmour(c.getArmour());
        doc.setTranslations(c.getTranslations());
        doc.setCardGroup(c.getCardGroup());
        doc.setFaction(c.getFaction());
        doc.setName(c.getName());
        doc.setPower(c.getPower());
        doc.setRarity(c.getRarity());
        return doc;
    };

    public CardDocument save(Card card) {
        return repository.save(converter.convert(card));
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
}
