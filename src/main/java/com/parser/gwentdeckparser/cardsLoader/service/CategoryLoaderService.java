package com.parser.gwentdeckparser.cardsLoader.service;

import com.parser.gwentdeckparser.cardStorage.model.CategoryDocument;
import com.parser.gwentdeckparser.cardStorage.model.embedded.EmbeddedTranslation;
import com.parser.gwentdeckparser.cardStorage.service.CategoryMongoStorageService;
import com.parser.gwentdeckparser.common.LocalisationEnum;
import com.parser.gwentdeckparser.deckStructure.deckBuilder.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryLoaderService {

    private final CategoryMongoStorageService categoryService;

    @Autowired
    public CategoryLoaderService(CategoryMongoStorageService categoryService) {
        this.categoryService = categoryService;
    }

    public void saveOrUpdateCategories(Card card, LocalisationEnum locale) {
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
}
