package org.combat.game;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File jsonFile = new File("src/main/utils/champions.json");
        ChampionsWrapper wrapper = mapper.readValue(jsonFile, ChampionsWrapper.class);
        List<Champion> champions = wrapper.getChampions();
    }
}
