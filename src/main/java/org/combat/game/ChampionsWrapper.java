package org.combat.game;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ChampionsWrapper {
    private static List<Champion> champions;
    final static Scanner scanner = new Scanner(System.in);
    private static String playerCommand;

    public List<Champion> getChampions() {
        return champions;
    }

    public void setChampions(List<Champion> newChampions) {
        champions = newChampions;
    }

    static public Champion getChampion(String championId) {
        Champion chosenChampion = null;
        for (Champion champion : champions) {
            if (Objects.equals(champion.getId(), championId)
                    || Objects.equalsIgnoreCase(champion.getName(), championId)) {
                chosenChampion = champion;
            }
        }
        return chosenChampion;
    }

    static public void handleInfoCommand(Player activePlayer) {
        do {
            System.out.print(activePlayer.name + " > ");
            playerCommand = scanner.nextLine().trim();

            String[] parts = playerCommand.split("\\s+");
            String command = parts.length > 0 ? parts[0].toLowerCase() : "";
            String champInput = parts.length > 1 ? parts[1] : "";

            if (command.equals("show")) {
                GameInfo.printChampions(champions);
                continue;
            }

            if (!command.equals("info")) {
                System.out.println("Invalid command! Use 'info <champ>' or 'show'.");
                continue;
            }

            if (champInput.isEmpty()) {
                System.out.println("Please provide a champion name or ID (e.g., 'info pyra').");
                continue;
            }

            Champion targetChampion = ChampionsWrapper.getChampion(champInput);
            if (targetChampion == null) {
                System.out.println("Champion '" + champInput + "' not found. Try again.");
                continue;
            }

            GameInfo.printChampionDetails(targetChampion);
            GameInfo.printCommands();
        } while (!playerCommand.equalsIgnoreCase("done"));
    }
}
