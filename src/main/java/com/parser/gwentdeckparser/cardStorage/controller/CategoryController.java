package com.parser.gwentdeckparser.cardStorage.controller;

import com.parser.gwentdeckparser.cardStorage.dto.CategoriesListDto;
import com.parser.gwentdeckparser.cardStorage.model.CategoryDocument;
import com.parser.gwentdeckparser.cardStorage.service.CategoryMongoStorageService;
import com.parser.gwentdeckparser.common.enums.LocalisationEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/storage/categories")
public class CategoryController {
    private final CategoryMongoStorageService categoryService;

    @Autowired
    public CategoryController(CategoryMongoStorageService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<CategoriesListDto> getCategories() {
        List<CategoryDocument> categories = categoryService.findAllCategories();
        return ResponseEntity.ok().body(toDto(categories));
    }

    private CategoriesListDto toDto(List<CategoryDocument> docs) {
        //TODO: move to mapper
        //TODO: get localizedName by localization parameter
        CategoriesListDto listDto = new CategoriesListDto();
        listDto.setAmount(docs.size());

        docs.forEach(doc -> {
            CategoriesListDto.CategoryDto dto = new CategoriesListDto.CategoryDto();
            dto.setGwentId(doc.getId());
            dto.setName(doc.getTranslations().get(LocalisationEnum.EN).getLocalizedName());
            listDto.setData(dto);
        });

        return listDto;
    }
}
