package com.parser.gwentdeckparser.deckGraber;

import com.parser.gwentdeckparser.deckStructure.deckBuilder.CardContainer;
import com.parser.gwentdeckparser.deckStructure.guide.Guide;
import com.parser.gwentdeckparser.deckStructure.guide.GuideList;
import com.parser.gwentdeckparser.exceptions.GwentParserException;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GwentClient {

    private final String GWENT_URL = "https://www.playgwent.com";
    private final String DECK_GUIDES = "/decks/api/guides";
    private final String DECK_BUILDER = "/decks/api/builder/search";
    private final String RANGE = "/offset/%s/limit/%s";

    private final OkHttpClient client = new OkHttpClient();
    private final GwentDataParser parser;

//    public BufferedSource leaders() {
//        var range = String.format(RANGE, 0, 0);
//        var req = new Request.Builder()
//                .url(GWENT_URL + "/en/"+ DECK_GUIDES + range)
//                .build();
//
//        return sendReq(req);
//    }

    public GuideList topDecks(long deckCount) {
        var range = String.format(RANGE, 0, deckCount);
        var req = new Request.Builder()
                .url(GWENT_URL + "/en/"+ DECK_GUIDES + range)
                .build();
        return sendReq(req, parser.getGuideListAdapter());
    }

    public Guide deck(long deckId, String locale) {
        var req = new Request.Builder()
                .url(GWENT_URL + "/en/" + DECK_GUIDES + "/" + deckId)
                .build();
        return sendReq(req, parser.getGuideAdapter());
    }

    public List<CardContainer> cardById(String cardId) {
        var filter = new HashMap<String, String>();
        filter.put("id", String.valueOf(cardId));
        return cardSearch(filter);
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
    public List<CardContainer> cardSearch(Map<String, String> filters) {
        var cardUrl = HttpUrl.parse(GWENT_URL + "/en/" + DECK_BUILDER).newBuilder();

        if (!filters.isEmpty()) {
            filters.entrySet().forEach(qp -> cardUrl.addQueryParameter(qp.getKey(), qp.getValue()));
        }

        var req = new Request.Builder().url(cardUrl.build().toString()).build();
        return sendReq(req, parser.getCardListAdapter());
    }

    private <G> G sendReq(Request request, JsonAdapter<G> adapter) {
        try {
            var call = client.newCall(request);
            var resp = call.execute();
            return adapter.fromJson(resp.body().source());
        } catch (IOException ex) {
            throw new GwentParserException(ex);
        } catch (JsonDataException dEx) {
            throw new GwentParserException("ParsingProblems", dEx);
        }
    }
}
