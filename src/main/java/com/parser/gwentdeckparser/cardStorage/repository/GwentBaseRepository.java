package com.parser.gwentdeckparser.cardStorage.repository;

import com.parser.gwentdeckparser.cardStorage.model.BaseGwentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface GwentBaseRepository<T extends BaseGwentEntity> extends MongoRepository<T, String> {

    @Query("{gwent_id: '?0'}")
    Optional<T> findByGwentObjectId(String gwentId);
}
