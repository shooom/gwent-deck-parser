package com.parser.gwentdeckparser.deckStructure.guide;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CardTooltip {
    private String type;
    private String key;
    private String value;
}
