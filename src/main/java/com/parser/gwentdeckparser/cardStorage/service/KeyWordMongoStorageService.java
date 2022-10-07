package com.parser.gwentdeckparser.cardStorage.service;

import com.parser.gwentdeckparser.cardStorage.model.KeyWordDocument;
import com.parser.gwentdeckparser.cardStorage.repository.KeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KeyWordMongoStorageService {
    private final KeywordRepository repository;

    @Autowired
    public KeyWordMongoStorageService(KeywordRepository repository) {
        this.repository = repository;
    }

    public KeyWordDocument save(KeyWordDocument doc) {
        return repository.save(doc);
    }

    public Optional<KeyWordDocument> findByGwentId(String gwentId) {
        return repository.findByGwentObjectId(gwentId);
    }

    public List<KeyWordDocument> getAllKeywords() {
        return repository.findAll();
    }
}
