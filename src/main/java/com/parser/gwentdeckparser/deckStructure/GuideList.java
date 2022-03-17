package com.parser.gwentdeckparser.deckStructure;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GuideList {
    private List<Guide> guides;
}
