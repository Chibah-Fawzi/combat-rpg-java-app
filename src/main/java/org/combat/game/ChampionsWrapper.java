package org.combat.game;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ChampionsWrapper {
    private static List<Champion> champions;
    final static Scanner scanner = new Scanner(System.in);
    public List<Champion> getChampions() {
        return champions;
    }
    private static String playerCommand;

    static public Champion getChampion(String championId){
        Champion chosenChampion = null;
        for(Champion champion : champions){
            if(Objects.equals(champion.getId(), championId)){
                chosenChampion= champion;
            }
        }
        return chosenChampion;
    }
    static public Champion setChampion(Champion champion){
        Champion chosenChampion = null;
        for(Champion champ : champions){
            if(Objects.equals(champ.getId(), champion)){
                chosenChampion= champ;
            }
        }
        return chosenChampion;
    }
    public void setChampions(List<Champion> newChampions) {
        champions = newChampions;
    }


    static public void selectChampion(Player activePlayer){

        do {
            System.out.print(activePlayer.name + " > ");
            playerCommand = scanner.nextLine().trim();

            // 1. Safe parsing into command and target champion
            String[] parts = playerCommand.split("\\s+");
            String command = parts.length > 0 ? parts[0].toLowerCase() : "";
            String champInput = parts.length > 1 ? parts[1] : "";

            // 2. Validate input format
            if (!command.equals("select") && !command.equals("info") && !command.equals("show")) {
                System.out.println("Invalid command! Use 'info <champ>' or 'select <champ>'.");
                continue;
            } else if (champInput.isEmpty() && !command.equals("show")) {
                System.out.println("Please provide a champion name or ID (e.g., '" + command + " pyra').");
                continue;
            }

            // 3. Fetch champion from wrapper
            Champion targetChampion = ChampionsWrapper.getChampion(champInput);
            // 4. Check if the select champion exists
            if (targetChampion == null && !command.equals("show")) {
                System.out.println("Champion '" + champInput + "' not found. Try again.");
                continue;
            }
            Champion firstPick = GameInfo.startingPlayer.getChosenChampion();

            // 5. Handle commands
                if(command.equals("show")) {
                    // Filter out the selected champion if Player 2 is picking
                    List<Champion> availableChampions = champions.stream()
                            .filter(champ -> firstPick == null || !champ.getId().equals(firstPick.getId()))
                            .toList();

                    GameInfo.printChampions(availableChampions);
                } else if (command.equals("info")) {
                    GameInfo.printChampionDetails(targetChampion);
                    GameInfo.printCommands();
                } else if (command.equals("select")) {
                    if (activePlayer != GameInfo.startingPlayer && firstPick != null && targetChampion.getId().equals(firstPick.getId())) {
                        System.out.println("You can't select the same champion as the starting player.");
                        continue;
                    }
                    activePlayer.setChosenChampion(targetChampion);
                    GameInfo.printUserSelection(activePlayer.name, activePlayer.getChosenChampion());

                }
        } while(activePlayer.getChosenChampion() == null);
    }

}