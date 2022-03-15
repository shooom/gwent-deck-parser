package com.parser.gwentdeckparser.deckGraber;

import com.parser.gwentdeckparser.deckStructure.Guide;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import okio.BufferedSource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DeckParser {
    private final Moshi moshi;
    private final JsonAdapter<Guide> guideAdapter;

    public DeckParser() {
        this.moshi = new Moshi.Builder().build();
        this.guideAdapter = this.moshi.adapter(Guide.class);
    }

    public Guide parseGuide(BufferedSource guideString) throws IOException {
        return this.guideAdapter.fromJson(guideString);
    }
}
