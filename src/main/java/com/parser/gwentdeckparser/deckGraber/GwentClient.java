package com.parser.gwentdeckparser.deckGraber;

import okhttp3.*;
import okio.BufferedSource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GwentClient {

    private final String GWENT_URL = "https://www.playgwent.com";
    private final String DECK_GUIDES = "/decks/api/guides";
    private final String DECK_RANGE = "/offset/%s/limit/%s";

    private final OkHttpClient client = new OkHttpClient();

    public String leaders() throws IOException {
        var range = String.format(DECK_RANGE, 0, 0);
        var req = new Request.Builder()
                .url(GWENT_URL + "/en/"+ DECK_GUIDES + range)
                .build();

        Call call = client.newCall(req);
        Response resp = call.execute();
        return resp.body().string();
    }

    public String topDecks(long deckCount) throws IOException {
        var range = String.format(DECK_RANGE, 0, deckCount);
        var req = new Request.Builder()
                .url(GWENT_URL + "/en/"+ DECK_GUIDES + range)
                .build();
        Call call = client.newCall(req);
        Response resp = call.execute();
        return resp.body().string();
    }

    public BufferedSource deck(long deckId, String locale) throws IOException {
        //TODO https://www.playgwent.com/ru/decks/api/guides/282083

        var req = new Request.Builder()
                .url(GWENT_URL + "/en/" + DECK_GUIDES + "/" + deckId)
                .build();
        Call call = client.newCall(req);
        Response resp = call.execute();
        return resp.body().source();
    }
}
