package com.parser.gwentdeckparser.deckStructure.deckBuilder;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Translation {
    private String name;
    private String tooltip;
    private String[] categories;
    private String fluff;
}
