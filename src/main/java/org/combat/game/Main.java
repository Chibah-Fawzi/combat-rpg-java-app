package org.combat.game;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.util.List;


import java.util.Scanner;
public class Main {

    public static void main(String[] args) throws IOException {
        // Call the object mapper
        ObjectMapper mapper = new ObjectMapper();
        // Read jsonFile values
        File jsonFile = new File("src/main/utils/champions.json");
        ChampionsWrapper wrapper = mapper.readValue(jsonFile, ChampionsWrapper.class);
        List<Champion> champions = wrapper.getChampions();

        // Introduction print
        GameInfo.printIntro();

        GameInfo.GameStatus currentStatus = GameInfo.GameStatus.DRAFT_PHASE;


        // Users inputs
        GameInfo.PlayerNames players = GameInfo.getPlayerNames();

        Player player1 = new Player(players.player1());
        Player player2 = new Player(players.player2());

        // Welcome prints
        GameInfo.printWelcome(player1,player2);
        // Champions print
        GameInfo.printChampions(champions);
        // Command print
        GameInfo.printCommands();

        // User champ selection
        ChampionsWrapper.selectChampion(player1);
        ChampionsWrapper.selectChampion(player2);

    }
}
