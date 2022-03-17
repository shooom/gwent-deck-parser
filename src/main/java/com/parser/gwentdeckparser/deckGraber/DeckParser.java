package com.parser.gwentdeckparser.deckGraber;

import com.parser.gwentdeckparser.deckStructure.Guide;
import com.parser.gwentdeckparser.deckStructure.GuideList;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import okio.BufferedSource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class DeckParser {
    private final Moshi moshi;
    private final JsonAdapter<Guide> guideAdapter;
    private final JsonAdapter<GuideList> guideListAdapter;

    public DeckParser() {
        this.moshi = new Moshi.Builder().build();
        this.guideAdapter = this.moshi.adapter(Guide.class);
        this.guideListAdapter = this.moshi.adapter(GuideList.class);
    }

    public Guide parseGuide(BufferedSource source) throws IOException {
        return this.guideAdapter.fromJson(source);
    }

    public GuideList parseGuideList(BufferedSource source) throws IOException {
        return guideListAdapter.fromJson(source);
    }
}
