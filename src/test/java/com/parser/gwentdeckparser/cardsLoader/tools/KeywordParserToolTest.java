package com.parser.gwentdeckparser.cardsLoader.tools;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KeywordParserToolTest {

    @Test
    void findAllMatches_success_whenStringHasAlotOfMatches() {
        String example = "<keyword=order>Order</keyword>: Draw a card, then <keyword=discard>Discard</keyword> a card and <keyword=spawn>Spawn</keyword> 2 Crows in your <keyword=melee>Melee</keyword> row.";
        String[] expectedResults = {
                "order:Order",
                "discard:Discard",
                "spawn:Spawn",
                "melee:Melee"
        };

        List<String> results = KeywordParserTool.findAllMatches(example);
        assertResults(expectedResults, results);
    }

    @Test
    public void findAllMatches_isSuccessful_whenDataIsEnglish() {
        String example = "<keyword=shield>Shield</keyword>. <keyword=deploy>Deploy</keyword>: Play a Witcher card from your deck. <keyword=adrenaline>Adrenaline</keyword> 4: Give it <keyword=shield>Shield</keyword>.";
        String[] expectedResults = {
                "shield:Shield",
                "deploy:Deploy",
                "adrenaline:Adrenaline",
                "shield:Shield"
        };

        List<String> results = KeywordParserTool.findAllMatches(example);
        assertResults(expectedResults, results);
    }

    @Test
    public void findAllMatches_isSuccessful_whenDataIsRussian() {
        String example = "<keyword=shield>Щит</keyword>. \\\\n<keyword=deploy>Размещение</keyword>: сыграйте ведьмака из вашей колоды.\\\\n<keyword=adrenaline>Адреналин</keyword> 4: добавьте ему <keyword=shield>щит</keyword>.";
        String[] expectedResults = {
                "shield:Щит",
                "deploy:Размещение",
                "adrenaline:Адреналин",
                "shield:щит"
        };

        List<String> results = KeywordParserTool.findAllMatches(example);
        assertResults(expectedResults, results);
    }

    @Test
    public void findAllMatches_isSuccessful_whenDataIsGerman() {
        String example = "<keyword=deploy>Einsatz</keyword> (<keyword=melee>Nahkampf</keyword>): Füge 3 gegnerischen Einheiten 2 Schaden zu und verschiebe sie dann in die <keyword=ranged>Fernkampf</keyword>-Reihe.";
        String[] expectedResults = {
                "deploy:Einsatz",
                "melee:Nahkampf",
                "ranged:Fernkampf"
        };

        List<String> results = KeywordParserTool.findAllMatches(example);
        assertResults(expectedResults, results);
    }

    private void assertResults(String[] expectedResults, List<String> results) {
        assertEquals(expectedResults.length, results.size());
        for (String str : expectedResults) {
            assertTrue(results.contains(str));
        }
    }
}