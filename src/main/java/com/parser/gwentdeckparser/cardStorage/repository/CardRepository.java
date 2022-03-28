package com.parser.gwentdeckparser.cardStorage.repository;

import com.parser.gwentdeckparser.cardStorage.model.CardDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CardRepository extends MongoRepository<CardDocument, String> {

    @Query("{name: '?0'}")
    CardDocument findCardByName(String name);

    @Query("{faction: '?0'}")
    List<CardDocument> findALlByFaction(String faction);
}
