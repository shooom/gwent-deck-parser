package com.parser.gwentdeckparser.cardStorage.repository;

import com.parser.gwentdeckparser.cardStorage.model.CategoryDocument;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends GwentBaseRepository<CategoryDocument> {
}
