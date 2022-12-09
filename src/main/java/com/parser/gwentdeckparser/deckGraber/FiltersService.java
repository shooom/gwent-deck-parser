package com.parser.gwentdeckparser.deckGraber;

import com.parser.gwentdeckparser.common.enums.Color;
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
                .collect(toMap(Faction::getName, Faction::getNum));
        var types = Arrays.stream(Type.values())
                        .collect(toMap(Type::getName, Type::getNum));
        var cardGroup = Arrays.stream(Color.values())
                        .collect(toMap(Color::getName, Color::getNum));
        filters.put("factions", factions);
        filters.put("type", types);
        filters.put("color", cardGroup);
        return filters;
    }
}
