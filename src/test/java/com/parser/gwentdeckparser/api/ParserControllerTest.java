package com.parser.gwentdeckparser.api;

import com.parser.gwentdeckparser.deckGraber.CardGrabberService;
import com.parser.gwentdeckparser.deckGraber.DeckGraberService;
import com.parser.gwentdeckparser.deckStructure.deckBuilder.Card;
import com.parser.gwentdeckparser.deckStructure.deckBuilder.CardContainer;
import com.parser.gwentdeckparser.exceptions.CardNotFoundException;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ParserController.class)
class ParserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeckGraberService deckGrabber;
    @MockBean
    private CardGrabberService cardGrabber;

    private Moshi moshi;
    private JsonAdapter<Card> cardJsonAdapter;

    @BeforeEach
    public void init() {
        moshi = new Moshi.Builder().build();
        cardJsonAdapter = moshi.adapter(Card.class);
    }


    @Test
    void getCardById_success() throws Exception {
        var result = "{\"id\":202544,\"craftingCost\":800,\"provisionCost\":15,\"availability\":14,\"keywords\":[\"doomed\",\"spawn\"],\"categoryNames\":[107],\"type\":8,\"primaryCategoryId\":107,\"secondaryFactions\":[],\"armour\":0,\"translations\":{\"en\":{\"name\":\"Masquerade Ball\",\"tooltip\":\"<keyword=doomed>Doomed</keyword>.\\\\nScenario: Progress whenever you play an Aristocrat on your side of the battlefield.\\\\nPrologue: <keyword=spawn>Spawn</keyword> a Thirsty Dame in this row.\\\\nChapter 1: <keyword=spawn>Spawn</keyword> and play Fangs of the Empire.\\\\nChapter 2: <keyword=spawn>Spawn</keyword> and play Fangs of the Empire.\",\"categories\":[\"Scenario\"],\"fluff\":\"Ophelie Van Moorlehem loves throwing huge galas. The fact that some attendees then disappear without a trace doesn't seem to bother anyone…\"}},\"cardGroup\":3,\"faction\":2,\"name\":\"Masked Ball\",\"power\":0,\"rarity\":25}";
        var id = "202544";
        var resObject = cardJsonAdapter.fromJson(result);

        Mockito.when(cardGrabber.getCardById(id)).thenReturn(resObject);

        mockMvc.perform(MockMvcRequestBuilders.get("/deck/cards/" + id))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getCardById_throws_exception() throws Exception {
        var id = "202544";
        var errorMsg = "Card with id=202544 not found";
        Mockito.when(cardGrabber.getCardById(id)).thenThrow(new CardNotFoundException(errorMsg));

        mockMvc.perform(MockMvcRequestBuilders.get("/deck/cards/" + id))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string(errorMsg));
    }
}