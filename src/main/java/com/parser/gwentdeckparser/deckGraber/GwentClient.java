package com.parser.gwentdeckparser.deckGraber;

import okhttp3.*;
import okio.BufferedSource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class GwentClient {

    private final String GWENT_URL = "https://www.playgwent.com";
    private final String DECK_GUIDES = "/decks/api/guides";
    private final String DECK_BUILDER = "/decks/api/builder/search";
    private final String RANGE = "/offset/%s/limit/%s";

    private final OkHttpClient client = new OkHttpClient();

    public String leaders() throws IOException {
        var range = String.format(RANGE, 0, 0);
        var req = new Request.Builder()
                .url(GWENT_URL + "/en/"+ DECK_GUIDES + range)
                .build();

        Call call = client.newCall(req);
        Response resp = call.execute();
        return resp.body().string();
    }

    public BufferedSource topDecks(long deckCount) throws IOException {
        var range = String.format(RANGE, 0, deckCount);
        var req = new Request.Builder()
                .url(GWENT_URL + "/en/"+ DECK_GUIDES + range)
                .build();
        Call call = client.newCall(req);
        Response resp = call.execute();
        return resp.body().source();
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

    /**
     * Filter types:
     * - id (Card id)
     * - keywords (Tags)
     * - faction (Faction)
     * - rarity (Crafting cost)
     * - type (Card type: Unit, Special, Artifact, Tactic?)
     * - card_group (Color - bronze, gold)
     * - availability (Card set)
     * - query (word from tooltips as Example)
     * @return
     */
    public String cardSearch(Map<String, String> filters) throws IOException {
        var cardUrl = HttpUrl.parse(GWENT_URL + "/en/" + DECK_BUILDER).newBuilder();

        if (!filters.isEmpty()) {
            filters.entrySet().forEach(qp -> cardUrl.addQueryParameter(qp.getKey(), qp.getValue()));
        }

        var req = new Request.Builder()
                .url(cardUrl.build().toString())
                .build();

        var call = client.newCall(req);
        var resp = call.execute();
        return resp.body().string();
    }

    public String cardById(String cardId) throws IOException {
        var filter = new HashMap<String, String>();
        filter.put("id", String.valueOf(cardId));
        String res = cardSearch(filter);
        return res;
    }
}
