package com.parser.gwentdeckparser.cardsLoader.service;

import com.parser.gwentdeckparser.cardStorage.model.KeyWordDocument;
import com.parser.gwentdeckparser.cardStorage.model.embedded.KeyWordTranslation;
import com.parser.gwentdeckparser.cardStorage.service.KeyWordMongoStorageService;
import com.parser.gwentdeckparser.common.LocalisationEnum;
import com.parser.gwentdeckparser.deckStructure.deckBuilder.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.parser.gwentdeckparser.cardsLoader.tools.KeywordParserTool.findAllMatches;

@Service
public class KeyWordLoaderService {
    private final KeyWordMongoStorageService keywordService;

    @Autowired
    public KeyWordLoaderService(KeyWordMongoStorageService keywordService) {
        this.keywordService = keywordService;
    }

    public List<KeyWordDocument> saveOrUpdateKeyword(Card card, LocalisationEnum locale) {
        List<KeyWordDocument> keywordDocs = new ArrayList<>();
        List<String> keywords = card.getKeywords();
        List<String> kwTranslations = findAllMatches(card.getTranslations().get(locale.getLocalisation()).getTooltip());
        Map<String, String> translations = parseTooltip(kwTranslations);

        for (String key : keywords) {
            KeyWordDocument doc = resolveKeyword(key);
            doc.getTranslations().put(locale, addTranslation(translations.get(key)));
            keywordDocs.add(keywordService.save(doc));
        }
        return keywordDocs;
    }

    private KeyWordTranslation addTranslation(String keyword) {
        return new KeyWordTranslation(keyword);
    }

    private String capitalizeFirstChar(String str) {
        if (str == null) {
            return null;
        }
        char first = str.toCharArray()[0];
        str.replace(first, Character.toUpperCase(first));

        return str;
    }

    private KeyWordDocument resolveKeyword(String gwentId) {
        return keywordService.findByGwentId(gwentId).orElse(new KeyWordDocument(gwentId));
    }

    private Map<String, String> parseTooltip(List<String> tooltip) {
        return tooltip.stream()
                .map(str -> str.split(":"))
                .map(str -> Map.entry(str[0], capitalizeFirstChar(str[1])))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (k1, k2) -> k1));
    }
}
