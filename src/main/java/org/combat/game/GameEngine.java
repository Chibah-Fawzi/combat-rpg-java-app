package org.combat.game;

import java.util.List;
import java.util.Scanner;

public class GameEngine {
    public int combatTurns = 1;
    public int combatRounds = 1;
    final static Scanner scanner = new Scanner(System.in);

    private void applyEffect(ActiveEffect effect, Player activePlayer){

        System.out.println("Effect "+ effect + " applied") ;
    }


    private void handleUpkeepPhase(Player activePlayer){
        // 1. Tick down spell cooldowns
        for (Spell activeSpell : activePlayer.chosenChampion.getSpells()) {
            int currentCooldown = activeSpell.getCurrentCooldown();
            if (currentCooldown > 0) {
                activeSpell.setCurrentCooldown(currentCooldown - 1);
            }
        }

        // 2. Apply active DoTs and decrement remaining turns
        List<ActiveEffect> effects = activePlayer.chosenChampion.getActiveEffects();
        if(effects != null && effects.toArray().length > 0) {
            for (ActiveEffect activeEffect : effects) {
                applyEffect(activeEffect, activePlayer); // Deals DoT damage, etc.
                activeEffect.decrementDuration();        // Each effect tracks its own duration!
            }
        // 3. Clean up expired effects
        effects.removeIf(ActiveEffect::isExpired);
        }



    }
    private void handleDisplayPhase(Player player){
        // Print turn number, HP/Mana bars, active debuffs, command options
        GameInfo.printChampionStats(player);
    }
    private void handleActionPhase(Champion activeChamp,Player activePlayer, Player defendingPlayer){
        GameInfo.printCombatStats(activePlayer, defendingPlayer, combatTurns, combatRounds);

        // Read input -> Execute Auto-Attack OR Cast Spell -> Apply damage/effects to defendingPlayer
        GameInfo.printCombatCommands(activeChamp);

        String playerCombatChoice = scanner.nextLine();

        while (!playerCombatChoice.equals("1") && !playerCombatChoice.equals("2") ) {
            System.out.println("Please enter a valid number");
            playerCombatChoice = scanner.nextLine();
        }

        GameInfo.handleCombatChoice(playerCombatChoice);
        System.out.println("Passed to next step");
    }
    private void playTurn(Player activePlayer, Player defendingPlayer){
        Champion chosenChampionActive = activePlayer.chosenChampion;
        Champion chosenChampionDefender = defendingPlayer.chosenChampion;
         handleUpkeepPhase(activePlayer);
         handleDisplayPhase(activePlayer);
         handleActionPhase(chosenChampionActive, activePlayer, defendingPlayer);

//        chosenChampionActive.getOneSpell(1)

    }
    public void startGame(Player activePlayer, Player player1, Player player2){

        // Add isAlive method in Player class that return true if currentHealth > 0;
        while(player1.isAlive() && player2.isAlive()){
            Player defendingPlayer = activePlayer == player1 ? player2 : player1;
            // Start the player turn
            playTurn(activePlayer, defendingPlayer);

            // Swap players
            activePlayer = defendingPlayer;
            combatTurns++;
            combatRounds = (int) Math.round((double) combatTurns /2);
        }
        // declare winner & display information about the game (damage, healing... etc);
        // Declare winner (whoever is alive)
        Player winner = player1.isAlive() ? player1 : player2;
        // GameInfo.printVictoryScreen(winner);v
    }

}
