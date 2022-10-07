package com.parser.gwentdeckparser.cardsLoader.tools;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeywordParserTool {
    private static final String KEY_WORD_REGEX_TEMPLATE = "([a-z]*)>(\\p{Alpha}*|\\W*)</";

    public static List<String> findAllMatches(String tooltipWithKeywords) {
        Pattern ptrn = Pattern.compile(KEY_WORD_REGEX_TEMPLATE);
        Matcher mtch = ptrn.matcher(tooltipWithKeywords);
        return mtch.results()
                .map(MatchResult::group)
                .map(str -> str.replaceAll("[=</]", ""))
                .map(str -> str.replaceAll(">", ":"))
                .toList();
    }
}
