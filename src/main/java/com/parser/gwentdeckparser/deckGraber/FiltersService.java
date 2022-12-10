package com.parser.gwentdeckparser.deckGraber;

import com.parser.gwentdeckparser.common.enums.CardGroupEnum;
import com.parser.gwentdeckparser.common.enums.Faction;
import com.parser.gwentdeckparser.common.enums.Type;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.toMap;

@Service
public class FiltersService {

    public Map<String, Map> getFilters() {
        var filters = new HashMap<String, Map>();
        var factions = Arrays.stream(Faction.values())
                .collect(toMap(Faction::getGwentName, Faction::getGwentNumber));
        var types = Arrays.stream(Type.values())
                        .collect(toMap(Type::getGwentName, Type::getGwentNumber));
        var cardGroup = Arrays.stream(CardGroupEnum.values())
                        .collect(toMap(CardGroupEnum::getGwentName, CardGroupEnum::getGwentNumber));
        filters.put("factions", factions);
        filters.put("type", types);
        filters.put("color", cardGroup);
        return filters;
    }
}
