package com.parser.gwentdeckparser.deckGraber;

import com.parser.gwentdeckparser.deckStructure.deckBuilder.CardContainer;
import com.parser.gwentdeckparser.deckStructure.guide.Guide;
import com.parser.gwentdeckparser.deckStructure.guide.GuideList;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Component
public class GwentDataParser {
    private final Moshi moshi;
    @Getter
    private final JsonAdapter<Guide> guideAdapter;
    @Getter
    private final JsonAdapter<GuideList> guideListAdapter;
    @Getter
    private final JsonAdapter<List<CardContainer>> cardListAdapter;

    public GwentDataParser() {
        this.moshi = new Moshi.Builder().build();
        this.guideAdapter = this.moshi.adapter(Guide.class);
        this.guideListAdapter = this.moshi.adapter(GuideList.class);

        Type cardListType = Types.newParameterizedType(List.class, CardContainer.class);
        this.cardListAdapter = this.moshi.adapter(cardListType);
    }
}
