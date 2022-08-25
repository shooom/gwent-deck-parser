package com.parser.gwentdeckparser.cardStorage.repository;

import com.parser.gwentdeckparser.cardStorage.model.CardDocument;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends GwentBaseRepository<CardDocument> {

    @Query("{name: '?0'}")
    CardDocument findCardByName(String name);

    @Query("{faction: '?0'}")
    List<CardDocument> findALlByFaction(String faction);
}
