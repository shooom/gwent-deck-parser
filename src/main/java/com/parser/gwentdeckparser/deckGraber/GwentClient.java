package com.parser.gwentdeckparser.deckGraber;

import com.parser.gwentdeckparser.deckStructure.deckBuilder.CardContainer;
import com.parser.gwentdeckparser.deckStructure.guide.Guide;
import com.parser.gwentdeckparser.deckStructure.guide.GuideList;
import com.parser.gwentdeckparser.exceptions.GwentParserException;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import lombok.RequiredArgsConstructor;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class GwentClient {

    private final String GWENT_URL = "https://www.playgwent.com";
    private final String DECK_GUIDES = "/decks/api/guides";
    private final String DECK_BUILDER = "/decks/api/builder/search";
    private final String RANGE = "/offset/%s/limit/%s";
    private static final String CARD_LEADER_TYPE = "1";

    private final OkHttpClient client = new OkHttpClient();
    private final GwentDataParser parser;

    public GuideList loadTopDecks(long deckCount) {
        var range = format(RANGE, 0, deckCount);
        var req = new Request.Builder()
                .url(GWENT_URL + "/en/"+ DECK_GUIDES + range)
                .build();
        return sendRequest(req, parser.getGuideListAdapter());
    }

    public Guide loadDeckById(long deckId, String locale) {
        var req = new Request.Builder()
                .url(GWENT_URL + "/en/" + DECK_GUIDES + "/" + deckId)
                .build();
        return sendRequest(req, parser.getGuideAdapter());
    }

    public List<CardContainer> loadLeaders(Map<String, String> filters) {
        filters.put("type", CARD_LEADER_TYPE);
        return searchCards(filters);
    }

    public List<CardContainer> loadCardById(String cardId, String locale) {
        var filters = new HashMap<String, String>();
        filters.put("id", String.valueOf(cardId));
        filters.put("locale", locale);
        return searchCards(filters);
    }

    /**
     * Filter types: <br/>
     * - id (Card id)<br/>
     * - keywords (Tags)<br/>
     * - faction (Faction)<br/>
     * - rarity (Crafting cost)<br/>
     * - type (Card type: Unit, Special, Artifact, Tactic?)<br/>
     * - card_group (Color - bronze, gold)<br/>
     * - availability (Card set)<br/>
     * - query (word from tooltips as Example)<br/>
     * - locale (language)
     * @return
     */
    public List<CardContainer> searchCards(Map<String, String> filters) {
        var cardUrl = HttpUrl
                .parse(GWENT_URL + "/" + filters.get("locale") + "/" + DECK_BUILDER).newBuilder();

        if (!filters.isEmpty()) {
            filters.entrySet()
                    .forEach(qp -> cardUrl.addQueryParameter(qp.getKey(), qp.getValue()));
        }

        var req = new Request.Builder().url(cardUrl.build().toString()).build();
        return sendRequest(req, parser.getCardListAdapter());
    }

    private <G> G sendRequest(Request request, JsonAdapter<G> adapter) {
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
