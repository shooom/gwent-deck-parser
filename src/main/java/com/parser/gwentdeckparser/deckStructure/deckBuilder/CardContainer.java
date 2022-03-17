package com.parser.gwentdeckparser.deckStructure.deckBuilder;

import com.squareup.moshi.Json;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CardContainer {
    @Json(name = "_source")
    private Card source;
}
