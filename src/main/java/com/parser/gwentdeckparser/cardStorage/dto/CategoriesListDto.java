package com.parser.gwentdeckparser.cardStorage.dto;

import java.util.ArrayList;
import java.util.List;

public class CategoriesListDto {
    private Integer amount;
    private List<CategoryDto> data;

    public CategoriesListDto() {
        this.amount = 0;
        this.data = new ArrayList<>();
    }
    public static class CategoryDto {
        private String gwentId;
        private String name;

        public String getGwentId() {
            return gwentId;
        }

        public void setGwentId(String gwentId) {
            this.gwentId = gwentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public List<CategoryDto> getData() {
        return data;
    }

    public void setData(CategoryDto data) {
        this.data.add(data);
    }
}
