package org.combat.game;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

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
        ChampionsWrapper.selectChampion(GameInfo.startingPlayer);
        ChampionsWrapper.selectChampion(GameInfo.startingPlayer == player1 ? player2 : player1);

        currentStatus = GameInfo.GameStatus.COMBAT_PHASE;


        GameInfo.printCombatStart(player1,player2);

        // Player health and mana initialization
        player1.initializeStats();
        player2.initializeStats();

        // Display champions stats
        GameInfo.printChampionStats(player1);
        GameInfo.printChampionStats(player2);

        // Create an instance of the engine for the new game.
        GameEngine engine = new GameEngine();
        engine.startGame(GameInfo.startingPlayer, player1, player2);
    }
}