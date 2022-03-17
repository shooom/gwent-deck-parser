package com.parser.gwentdeckparser.deckGraber;

import com.parser.gwentdeckparser.deckStructure.deckBuilder.Card;
import com.parser.gwentdeckparser.deckStructure.deckBuilder.CardContainer;
import com.parser.gwentdeckparser.deckStructure.guide.Guide;
import com.parser.gwentdeckparser.deckStructure.guide.GuideList;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import okio.BufferedSource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GwentDataParser {
    private final Moshi moshi;
    private final JsonAdapter<Guide> guideAdapter;
    private final JsonAdapter<GuideList> guideListAdapter;
    private final JsonAdapter<List<CardContainer>> cardListAdapter;

    public GwentDataParser() {
        this.moshi = new Moshi.Builder().build();
        this.guideAdapter = this.moshi.adapter(Guide.class);
        this.guideListAdapter = this.moshi.adapter(GuideList.class);

        Type cardListType = Types.newParameterizedType(List.class, CardContainer.class);
        this.cardListAdapter = this.moshi.adapter(cardListType);
    }

    public Guide parseGuide(BufferedSource source) throws IOException {
        return this.guideAdapter.fromJson(source);
    }

    public GuideList parseGuideList(BufferedSource source) throws IOException {
        return guideListAdapter.fromJson(source);
    }

    public List<Card> parseCardList(BufferedSource source) throws IOException {
        return cardListAdapter.fromJson(source)
                .stream()
                .map(container -> container.getSource())
                .collect(Collectors.toList());
    }
}
