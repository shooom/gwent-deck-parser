package com.parser.gwentdeckparser.cardStorage.service;

import com.parser.gwentdeckparser.cardStorage.model.CardDocument;
import com.parser.gwentdeckparser.cardStorage.repository.CardRepository;
import com.parser.gwentdeckparser.exceptions.CardNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardMongoStorageService {

    private final CardRepository repository;

    public CardDocument save(CardDocument card) {
        return repository.save(card);
    }

    public CardDocument getByName(String name) {
        return repository.findCardByName(name);
    }

    public Optional<CardDocument> findByGwentId(String gwentId) {
        return repository.findByGwentObjectId(gwentId);
    }

    public CardDocument getByGwentId(String gwentId) {
        return findByGwentId(gwentId)
                .orElseThrow(() -> new CardNotFoundException(String.format("Card with Gwent-ID %s not found", gwentId)));

    }
}
