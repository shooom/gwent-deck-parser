package com.parser.gwentdeckparser.cardStorage.repository;

import com.parser.gwentdeckparser.cardStorage.model.CardDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends MongoRepository<CardDocument, String> {

    @Query("{name: '?0'}")
    CardDocument findCardByName(String name);

    @Query("{gwent_id: '?0'}")
    Optional<CardDocument> findByGwentObjectId(String gwentId);

    @Query("{faction: '?0'}")
    List<CardDocument> findALlByFaction(String faction);


}
