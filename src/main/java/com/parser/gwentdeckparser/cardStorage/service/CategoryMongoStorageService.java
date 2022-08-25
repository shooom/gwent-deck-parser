package com.parser.gwentdeckparser.cardStorage.service;

import com.parser.gwentdeckparser.cardStorage.model.CategoryDocument;
import com.parser.gwentdeckparser.cardStorage.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryMongoStorageService {
    private final CategoryRepository repository;

    @Autowired
    CategoryMongoStorageService(CategoryRepository repository) {
        this.repository = repository;
    }

    public CategoryDocument saveCategory(CategoryDocument doc) {
        return repository.save(doc);
    }

    public Optional<CategoryDocument> findByGwentId(String gwentId) {
        return repository.findByGwentObjectId(gwentId);
    }

    public List<CategoryDocument> findAllCategories() {
        return repository.findAll();
    }
}
