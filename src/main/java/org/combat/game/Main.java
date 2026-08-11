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

        GameInfo.printIntro();

        GameInfo.PlayerNames players = GameInfo.getPlayerNames();
        Player player1 = new Player(players.player1());
        Player player2 = new Player(players.player2());

        GameInfo.printWelcome(player1, player2);
        GameInfo.printChampions(champions);
        GameInfo.printCommands();

        ChampionsWrapper.selectChampion(player1);
        ChampionsWrapper.selectChampion(player2);
    }
}
